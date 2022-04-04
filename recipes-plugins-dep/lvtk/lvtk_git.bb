SUMMARY = "LV2 Toolkit"
DESCRIPTION = "This software package contains libraries that wrap the LV2 C API and extensions into easy to use C++ classes."
SECTION = "devel"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/lvtk/lvtk.git;protocol=git;branch=master \
"
SRCREV="6bfe981dfb5b27ea199dd4f6801b5305ca0355f9"

S = "${WORKDIR}/git"

PV = "2.0.0"

inherit waf pkgconfig

do_configure () {
    ${S}/waf configure --prefix=/usr --disable-examples --disable-ui
}

do_compile () {
    ${S}/waf build
}

do_install () {
    ${S}/waf install --destdir=${D}
}

DEPENDS = "\
	lv2 \
"

RDEPENDS_${PN} = " \
"

RPROVIDES_${PN} = " \
    lvtk-2 \
"

FILES_${PN} = "\
    /usr/include/lvtk-2 \
	/usr/lib \
"
