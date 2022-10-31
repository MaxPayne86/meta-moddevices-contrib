SUMMARY = "Dragonfly Reverb lv2 plugin"
DESCRIPTION = "Dragonfly Reverb is a bundle of free audio effects for Linux, MacOS, and Windows."
SECTION = "lv2/unstable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

#INSANE_SKIP_${PN} = "already-stripped"
#INSANE_SKIP_${PN} += " installed-vs-shipped"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    gitsm://github.com/michaelwillis/dragonfly-reverb.git;protocol=https;branch=master \
"
SRCREV="0e1413decb1a652d68ab14b8c718ceb8d5d676dc"

S = "${WORKDIR}/git"

inherit pkgconfig

EXTRA_OEMAKE = "'NOOPT=true'"

DEPENDS += " \
    lv2 \
    jack \
"

FILES_${PN} = "\
    ${LV2_DIR_BAD} \
"
