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
    #/usr/bin/jackd -R -P 80 -S -t 200 -C /tmp/jack-internal-session.conf -d alsa -d hw:$CARD -r $SR -p $BUFFER -n 2 -X seq &> /tmp/jackd.log &
    /usr/bin/jackd -R -P 80 -S -t 200 -C /tmp/jack-internal-session.conf -d alsa -d hw:$CARD -r $SR -p $BUFFER -n 2 &> /tmp/jackd.log &
    echo $! > /var/run/jackd.pid
}

stop_jackd()
{
    kill `cat /var/run/jackd.pid`
}

start_modttymidi()
{
    /usr/bin/jack_wait -w 2> /dev/null
    /usr/local/bin/mod-ttymidi -s /dev/$TTY -b 31250 -n ttymidi -r 70 &> /tmp/mod-ttymidi.log &
    echo $! > /var/run/mod-ttymidi.pid
}

stop_modttymidi()
{
    kill `cat /var/run/mod-ttymidi.pid`
}

start_browsepy()
{
    nice -n 17 /usr/bin/browsepy --directory /home/root/user-files --upload /home/root/user-files --removable /home/root/user-files 0.0.0.0 8081 &> /tmp/browsepy.log &
    echo $! > /var/run/browsepy.pid
}

stop_browsepy()
{
    kill `cat /var/run/browsepy.pid`
}

start_modui()
{
    #export HOME=/home/root
    #export LV2_PATH=$LV2_PATH:/usr/lib/lv2:/home/root/.lv2plugins
    mkdir -p /home/root/.pedalboards
    mkdir -p /home/root/.data/mod-ui
    mkdir -p /home/root/user-files
    export LV2_PLUGIN_DIR=/usr/lib/lv2
    #export LV2_PEDALBOARDS_DIR=/home/root/.pedalboards/
    export MOD_DATA_DIR=/home/root/.data/mod-ui
    export MOD_DEV_ENVIRONMENT=0
    export MOD_DEVICE_WEBSERVER_PORT=8888
    #export MOD_LOG=1
    export MOD_LOG=0
    export MOD_APP=0
    export MOD_DEV_HMI=0
    #export MOD_HMI_SERIAL_PORT="/dev/ttyUSB1"
    export MIDI_CH_PEDALBOARDS_NAV=1
    export MIDI_CH_SNAPSHOTS_NAV=2
    export MOD_USER_FILES_DIR=/home/root/user-files
    cd /usr/local/mod-ui
    nice -n 17 /usr/bin/python3 -u server.py &> /tmp/mod-ui.log &
    echo $! > /var/run/mod-ui.pid
}

stop_modui()
{
    kill `cat /var/run/mod-ui.pid`
}

case "$1" in
  start)
        start_jackd
        #start_modttymidi
        start_browsepy
        start_modui
        ;;
  stop)
        stop_modui
        stop_browsepy
        #stop_modttymidi
        stop_jackd
        ;;
  restart)
        $0 stop
        $0 start
        ;;
  *)
        echo "Usage: $0 { start | stop | restart }" >&2
        exit 1
        ;;
esac

exit 0
