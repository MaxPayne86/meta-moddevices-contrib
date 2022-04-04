# Recipe to install mod-host software
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INSANE_SKIP_${PN} = "already-stripped"
#INSANE_SKIP_${PN} += " installed-vs-shipped"

S = "${WORKDIR}/git"

SRC_URI = "\
    git://github.com/moddevices/mod-host.git;protocol=git;branch=hotfix-1.11 \
"
SRCREV="794e1fd8861e2a92b56bb89b33a59305c3e119cb"

inherit pkgconfig

do_compile () {
    oe_runmake HAVE_NE10=true
}

do_install () {
    oe_runmake install DESTDIR=${D} PREFIX=/usr
}

DEPENDS = "\
    lilv \
    jack \
    cc-master \
    ne10 \
"

RDEPENDS_${PN} = "\
    cc-master \
    ne10 \
"

FILES_${PN} += " \
    /usr/bin \
    /usr/lib \
    /usr/bin/mod-host \
    /usr/lib/jack \
    /usr/lib/jack/mod-host.so \
    /usr/lib/jack/mod-monitor.so \
"

