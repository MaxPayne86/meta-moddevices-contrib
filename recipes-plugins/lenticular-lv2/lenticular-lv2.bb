SUMMARY = "Lenticular lv2 plugin suite from Polyeffects"
DESCRIPTION = "A bundle of interesting plugins based on units from Mutable Instruments"
SECTION = "lv2/unstable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INSANE_SKIP_${PN} = "already-stripped"
#INSANE_SKIP_${PN} += " installed-vs-shipped"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    gitsm://github.com/polyeffects/lenticular_lv2.git;protocol=git;branch=master \
    file://fix-lv2-includes.patch \
    file://remove-debug-flags.patch \
"
SRCREV="ab7bdb81cd69eecba609ea7373a878e21658a763"

S = "${WORKDIR}/git"

inherit pkgconfig

EXTRA_OEMAKE = "'DESTDIR=${D}' 'LV2DIR=${LV2_DIR_BAD}'"

do_install () {
    oe_runmake install
}

DEPENDS += " \
    lv2 \
    jack \
"

FILES_${PN} = "\
    ${LV2_DIR_BAD} \
"
