SUMMARY = "A reference yocto sdk image for building software running on devices such as those produced by Moddevices, community contributed"
HOMEPAGE = "https://github.com/MaxPayne86/meta-moddevices-contrib"
LICENSE = "MIT"

IMAGE_LINGUAS = "en-us"

inherit core-image

require moddevices-contrib.inc

IMAGE_INSTALL += "\
    ${CORE_IMAGE_EXTRA_INSTALL} \
    ${ALSA} \
    ${JACK} \
    ${AUDIOLIBRARIES} \
"

export IMAGE_BASENAME = "moddevices-contrib-sdk-image"
