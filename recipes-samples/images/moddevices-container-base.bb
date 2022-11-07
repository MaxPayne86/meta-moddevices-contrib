SUMMARY = "Container image running moddevices software stack, a lv2 host controlled by a web ui"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

require recipes-extended/images/container-base.bb
require recipes-samples/images/moddevices-contrib.inc

OCI_IMAGE_ENTRYPOINT = "/usr/bin/sh"
CONTAINER_SHELL = "busybox"

IMAGE_INSTALL_append = " \
    ${CONTAINER_SHELL} \
    alsa-utils \
    ${JACK} \
    ${MODDEVICES} \
    container-entrypoint \
"
