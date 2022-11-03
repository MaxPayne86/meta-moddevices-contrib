#!/bin/sh

# Entrypoint script for moddevices-container

# Additional env variables
export LV2_PATH=$LV2_PATH:/usr/lib/lv2
export SNDFILE_JACKPLAY_AUTOCONNECT=mod-host:monitor-in_%d

start_jackd()
{
    cat > /tmp/jack-internal-session.conf <<EOF
# config for mod-live-usb
l mod-host mod-host 5555
#l mod-peakmeter mod-peakmeter container
EOF
    # @TODO: add snd-seq module
    #/usr/bin/jackd -R -P 80 -S -t 200 -C /tmp/jack-internal-session.conf -d alsa -d hw:$CARD -r $SR -p $BUFFER -n 2 -X seq 2>&1|awk '{print "[jackd]: "$0}' > /dev/stdout &
    /usr/bin/jackd -R -P 80 -S -t 200 -C /tmp/jack-internal-session.conf -d alsa -d hw:$CARD -r $SR -p $BUFFER -n 2 2>&1|awk '{print "[jackd]: "$0}' > /dev/stdout &
}

start_modttymidi()
{
    /usr/bin/jack_wait -w 2> /dev/null
    /usr/local/bin/mod-ttymidi -s /dev/$TTY -b 31250 -n ttymidi -r 70 2>&1|awk '{print "[ttymidi]: "$0}' > /dev/stdout &
}

start_browsepy()
{
    mkdir -p /home/root/user-files
    /usr/bin/browsepy --directory /home/root/user-files --upload /home/root/user-files --removable /home/root/user-files 0.0.0.0 8081 2>&1|awk '{print "[browsepy]: "$0}' > /dev/stdout &
}

start_modui()
{
    #export HOME=/home/root
    #export LV2_PATH=$LV2_PATH:/usr/lib/lv2:/home/root/.lv2plugins
    mkdir -p /home/root/.pedalboards
    mkdir -p /home/root/.data/mod-ui
    export LV2_PLUGIN_DIR=/usr/lib/lv2
    #export LV2_PEDALBOARDS_DIR=/home/root/.pedalboards/
    export MOD_DATA_DIR=/home/root/.data/mod-ui
    export MOD_DEV_ENVIRONMENT=0
    export MOD_DEVICE_WEBSERVER_PORT=8080
    #export MOD_LOG=1
    export MOD_LOG=0
    export MOD_APP=0
    export MOD_DEV_HMI=0
    #export MOD_HMI_SERIAL_PORT="/dev/ttyUSB1"
    export MIDI_CH_PEDALBOARDS_NAV=1
    export MIDI_CH_SNAPSHOTS_NAV=2
    export MOD_USER_FILES_DIR=/home/root/user-files
    cd /usr/local/mod-ui
    /usr/bin/python3 -u server.py 2>&1|awk '{print "[modui]: "$0}' > /dev/stdout &
}

start_jackd
#start_modttymidi
start_browsepy
start_modui
wait -n
exit $?
