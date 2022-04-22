SUMMARY = "Include file for moddevices-contrib image"
HOMEPAGE = "https://github.com/MaxPayne86/meta-moddevices-contrib"
LICENSE = "MIT"

ALSA = "\
    alsa-dev \
    libasound \
    alsa-plugins \
    alsa-utils \
    alsa-utils-scripts \
"

JACK = "\
    jack-server \
    libjack \
    jack-utils \
"

JACK_EXTRA = "\
    jack-capture \
    jack-smf-utils \
"

PYTHONSTUFF = "\
    python3-pyserial \
    python3-tornado \
    python3-pillow \
    python3-pystache \
    python3-modules \
    python3-pydoc \
    python3-smbus \
    python3-websocket-client \
    python3-requests \
"

AUDIOPLUGINS = "\
    calf \
    caps-lv2 \
    tap-lv2 \
    guitarix-lv2 \
    gxplugins \
    gxswitchlesswah-lv2 \
    gxvoxtb-lv2 \
    freaked \
    zam-plugins \
    x42-plugins \
    x42-stepseq \
    x42-tinygain \
    distrho-ports \
    dpf-plugins \
    shiro-plugins \
    bolliedelay \
    fomp \
    artyfx \
    infamous-plugins \
    loopor \
    screcord \
    invada-lv2 \
    alo-lv2 \
    fluidsynth-plug \
    zeroconvo-lv2  \
    rkrlv2 \
    sfizz-lv2 \
    lsp-plugins \
    lenticular-lv2 \
    pitchtomidi-lv2 \
    carla-plugins \
    die-plugins \
"

SYNTHENGINES = "\
    fluidsynth \
    setbfree \
    setbfree-controller \
    dexed \
    zynaddsubfx \
    amsynth \
"

AUDIOLIBRARIES = "\
    lv2 \
    serd \
    sord \
    sratom \
    lilv \
    zita-convolver \
    zita-resampler \
    liblo \
    portaudio-v19 \
"

MODPLUGINS = "\
    mod-mda-lv2 \
    mod-distortion \
    mod-utilities \
    mod-pitchshifter \
    mod-midi-utilities \
    sooper-looper-lv2 \
    modmeter \
    mod-ams-lv2 \
    mod-cv-plugins \
    mod-cabsim-ir-loader \
    mod-lv2-data \
    lv2-data-creative-commons \
"

MODDEVICES = "\
    mod-ttymidi \
    mod-host \
    mod-ui \
    cc-master \
"