# Recipe to install cc-master
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

#INSANE_SKIP_${PN} = "ldflags"
#INSANE_SKIP_${PN} = "installed-vs-shipped"
INSANE_SKIP_${PN} = "file-rdeps"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/moddevices/cc-master.git;protocol=git;branch=master \
    file://0001-Fixed-linkage-of-jansson-in-libcc_client.patch \
    file://0002-Fixed-include-path-in-pkg-config-description-files.patch \
    file://0003-Statically-linking-in-libcc_client.patch \
"
SRCREV="90ee28458f8e01d2fcdb7c2ec2d61b42312671b4"

S = "${WORKDIR}/git"

inherit waf pkgconfig

EXTRA_OECONF = "--prefix=/usr"

RPROVIDES_${PN} += " \
    libcc_client \
    libcontrolchain \
"

DEPENDS = "\
    libserialport \
    jansson \
"

RDEPENDS_${PN} = "\
    libserialport \
    jansson \
    libcontrolchain \
"

FILES_${PN} += " \
    /usr/include \
    /usr/bin \
    /usr/lib \
    /usr/lib/libcc_client.so \
    /usr/lib/libcontrolchain.so \
"

FILES_${PN}-dev = " \
    ${includedir} \
"