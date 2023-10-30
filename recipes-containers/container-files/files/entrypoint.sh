#!/bin/sh

# Entrypoint script for moddevices-container

# Additional env variables
#export LD_BIND_NOW=1
export LV2_PATH=$LV2_PATH:/usr/lib/lv2
export SNDFILE_JACKPLAY_AUTOCONNECT=mod-host:monitor-in_%d

get_soundcard_number_from_id()
{
    ID=$1
    for item in $(ls /sys/class/sound/card*/id); do
        id=$(cat $item)
        if [ "$id" = "$ID" ]; then
            number=$(cat $(echo $item | sed -e 's/\/id$//')"/number")
            return $number
        fi
    done
    return -1
}

start_jackd()
{
    cat > /tmp/jack-internal-session.conf <<EOF
# config for mod-live-usb
l mod-host mod-host 5555
#l mod-peakmeter mod-peakmeter container
EOF
    if [ "$FMT" = "16le" ]; then
        /usr/bin/jackd -R -P 80 -S -t 200 -C /tmp/jack-internal-session.conf -d alsa -d hw:$CARD -S -r $SR -p $BUFFER -n 2 -X seq 2>&1|awk '{print "[jackd]: "$0}' > /dev/stdout &
    else
        /usr/bin/jackd -R -P 80 -S -t 200 -C /tmp/jack-internal-session.conf -d alsa -d hw:$CARD -r $SR -p $BUFFER -n 2 -X seq 2>&1|awk '{print "[jackd]: "$0}' > /dev/stdout &
    fi
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

# Specific Arduino stuff, apply custom settings
# depending on board / carrier board combination
if [ -e /run/arduino_hw_info.env ]; then
    . /run/arduino_hw_info.env
    if [ "$BOARD" = "portenta-x8" ]; then
        if [ "$CARRIER_NAME" = "max" ]; then
            echo "Applying Arduino Portenta-X8 settings for Max carrier board"
            export CARD="cs42l52audio"
            export FMT="16le"
            echo "Configuring soundcard $CARD"
            get_soundcard_number_from_id $CARD
            n=$?
            if [ $n -lt 0 ]; then
                echo "Error! Cannot identify hw:n for soundcard $CARD"
                exit 1
            fi
            amixer -c $n sset 'ADC Left Mux' 'Input2A'
            amixer -c $n sset 'ADC Right Mux' 'Input2B'

            #amixer -c $n sset 'HP Left Amp' on
            #amixer -c $n sset 'HP Right Amp' on
            amixer -c $n sset 'Master' 204 # 0dB
            amixer -c $n sset 'Headphone' 192 # 0dB

            #amixer -c $n sset 'SPK Left Amp' on
            #amixer -c $n sset 'SPK Right Amp' on
            amixer -c $n sset 'Speaker' 192 # 0dB
        elif [ "$CARRIER_NAME" = "ditto" ]; then
            if [ "$HAT_NAME" = "thestomp" ]; then
                echo "Applying Arduino Portenta-X8 settings for Ditto carrier board and TheStomp! hat"
                export CARD="cs42l52audio"
                export FMT="16le"
                echo "Configuring soundcard $CARD"
                get_soundcard_number_from_id $CARD
                n=$?
                if [ $n -lt 0 ]; then
                    echo "Error! Cannot identify hw:n for soundcard $CARD"
                    exit 1
                fi
                amixer -c $n sset 'ADC Left Mux' 'Input1A'
                amixer -c $n sset 'ADC Right Mux' 'Input1B'

                #amixer -c $n sset 'HP Left Amp' on
                #amixer -c $n sset 'HP Right Amp' on
                amixer -c $n sset 'Master' 204 # 0dB
                amixer -c $n sset 'Headphone' 192 # 0dB

                #amixer -c $n sset 'SPK Left Amp' on
                #amixer -c $n sset 'SPK Right Amp' on
                amixer -c $n sset 'Speaker' 192 # 0dB
            fi
        fi
    fi
fi

if [ "$CARD" = "da7213audio" ]; then
    echo "Configuring soundcard $CARD"
    get_soundcard_number_from_id $CARD
    n=$?
    if [ $n -lt 0 ]; then
        echo "Error! Cannot identify hw:n for soundcard $CARD"
        exit 1
    fi
    amixer -c $n sset 'Mixout Left DAC Left' unmute
    amixer -c $n sset 'Mixout Right DAC Right' unmute
    amixer -c $n sset 'Headphone' unmute
    amixer -c $n sset 'Lineout' unmute
    amixer -c $n sset 'Lineout' 81
fi

if [ -z $CARD ]; then
    echo "Error! No soundcard specified please set CARD env variable"
    exit 1
fi

start_jackd
#start_modttymidi
start_browsepy
start_modui
wait -n
exit $?
