# Recipe to install mod-host software
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INSANE_SKIP_${PN} = "already-stripped"
#INSANE_SKIP_${PN} += " installed-vs-shipped"

S = "${WORKDIR}/git"

SRC_URI = "\
    git://github.com/moddevices/mod-host.git;protocol=https;branch=master \
    file://01_fix-libjack-dir.patch \
    file://02_connect-all-networks.patch \
"
SRCREV="2b91ff30da05672fb079bd6bf0a812a975b1048e"

inherit pkgconfig

CFLAGS_append = "-ffast-math"

do_compile () {
    oe_runmake
}

do_install () {
    oe_runmake install DESTDIR=${D} PREFIX=/usr
}

DEPENDS = "\
    lilv \
    jack \
    fftw \
    readline \
"

FILES_${PN} += " \
    /usr/bin \
    /usr/lib \
    /usr/bin/mod-host \
    /usr/lib/jack \
    /usr/lib/jack/mod-host.so \
    /usr/lib/jack/mod-monitor.so \
"