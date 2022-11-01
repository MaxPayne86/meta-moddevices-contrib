SUMMARY = "Container for running moddevices software, a lv2 host controlled by a web ui"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

require recipes-extended/images/container-base.bb
require recipes-samples/images/moddevices-contrib.inc

OCI_IMAGE_ENTRYPOINT = "/usr/bin/sh"
CONTAINER_SHELL = "busybox"

IMAGE_INSTALL_append = "\
    coreutils \
    ${ALSA} \
    ${JACK} \
    ${PYTHONSTUFF} \
    ${MODDEVICES} \
    aidadsp-lv2 \
    ${AUDIOLIBRARIES} \
    container-files \
"
