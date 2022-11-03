#!/bin/sh

# Entrypoint script for moddevices-container

# Additional env variables
#export LD_BIND_NOW=1
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
    mkdir -p /home/root/user-files/'SF2 Instruments'
    mkdir -p /home/root/user-files/'SFZ Instruments'
    mkdir -p /home/root/user-files/'Audio Loops'
    mkdir -p /home/root/user-files/'Audio Recordings'
    mkdir -p /home/root/user-files/'Audio Samples'
    mkdir -p /home/root/user-files/'Audio Tracks'
    mkdir -p /home/root/user-files/'MIDI Clips'
    mkdir -p /home/root/user-files/'MIDI Songs'
    mkdir -p /home/root/user-files/'Speaker Cabinets IRs'
    mkdir -p /home/root/user-files/'Reverb IRs'
    mkdir -p /home/root/user-files/'Model SIMs'
    /usr/bin/browsepy --directory /home/root/user-files --upload /home/root/user-files --removable /home/root/user-files 0.0.0.0 8081 2>&1|awk '{print "[browsepy]: "$0}' > /dev/stdout &
}

start_modui()
{
    /usr/bin/jack_wait -w 2> /dev/null
    mkdir -p /home/root/.pedalboards
    #export MOD_LOG=1
    export MOD_DEV_HMI=1
    export MOD_KEYS_PATH=/root/keys/
    export MOD_USER_FILES_DIR="/home/root/user-files"
    export MOD_DEVICE_WEBSERVER_PORT=8888
    /usr/bin/python3 /usr/bin/mod-ui 2>&1|awk '{print "[modui]: "$0}' > /dev/stdout &
}

start_jackd
#start_modttymidi
start_browsepy
start_modui
wait -n
exit $?
