# Recipe to install modmeter lv2 plugin
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INSANE_SKIP_${PN} = "already-stripped"
#INSANE_SKIP_${PN} += " installed-vs-shipped"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/x42/modmeter.lv2.git;protocol=git;branch=master \
"
SRCREV="5927f9623473b031d2e281af503cab1ca6d917d0"

S = "${WORKDIR}/git"

inherit pkgconfig

EXTRA_OEMAKE = "'MOD=1'"

do_install() {
    oe_runmake install DESTDIR=${D} LV2DIR=${LV2_DIR}
}

DEPENDS = " \
    lilv \
"

RDEPENDS_modmeter = "\
"

FILES_${PN} = "\
    ${LV2_DIR} \
"

