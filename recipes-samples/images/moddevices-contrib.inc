SUMMARY = "Include file for moddevices-contrib image"
HOMEPAGE = "https://github.com/MaxPayne86/meta-moddevices-contrib"
LICENSE = "MIT"

ALSA = "\
    alsa-utils \
"

JACK = "\
    jack-server \
    libjack \
    jack-utils \
"

JACK_EXTRA = "\
    jack-capture \
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
    distrho-ports-v6 \
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
    rkrlv2 \
    sfizz-lv2 \
    lsp-plugins \
    carla-plugins \
    die-plugins \
    cardinal \
    modspectre \
    screcord \
    x42-zconv \
"

# A few extra plugins not included in
# mod-plugin-builder
AUDIOPLUGINS_EXTRA = "\
    lenticular-lv2 \
    pitchtomidi-lv2 \
    abninjam-lv2 \
    aidadsp-lv2 \
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
"
