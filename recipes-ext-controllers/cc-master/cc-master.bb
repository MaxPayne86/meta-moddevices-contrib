# Recipe to install cc-master
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/moddevices/cc-master.git;protocol=git;branch=master \
"
SRCREV="cab4047bc7eb199259e1c9f615e71d186199a13f"

S = "${WORKDIR}/git"

inherit waf pkgconfig

WAF_PYTHON = "python"

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