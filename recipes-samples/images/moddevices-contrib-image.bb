SUMMARY = "A reference yocto image for building devices such as those produced by Moddevices, community contributed"
HOMEPAGE = "https://github.com/MaxPayne86/meta-moddevices-contrib"
LICENSE = "MIT"

IMAGE_LINGUAS = "en-us"

inherit core-image

IMAGE_FEATURES += "ssh-server-dropbear"

require moddevices-contrib.inc

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    packagegroup-core-full-cmdline \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"

IMAGE_INSTALL += "\
    ${ALSA} \
    ${JACK} \
    ${PYTHONSTUFF} \
    ${MODDEVICES} \
    ${MODPLUGINS} \
    ${AUDIOPLUGINS} \
    ${AUDIOLIBRARIES} \
    ${SYNTHENGINES} \
"

export IMAGE_BASENAME = "moddevices-contrib-image"