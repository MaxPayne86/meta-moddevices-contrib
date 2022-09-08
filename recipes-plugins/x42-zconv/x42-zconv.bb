# INFO: this plugin suite uses fftw single precision calls
SUMMARY = "Lv2 plugin x42-zconv"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
BUNDLEDIR = "${@bb.utils.contains('SECTION', 'lv2/stable', '${LV2_DIR}', '${LV2_DIR_BAD}', d)}"

INSANE_SKIP_${PN} = "already-stripped"
#INSANE_SKIP_${PN} += " installed-vs-shipped"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/x42/zconvo.lv2.git;protocol=https;branch=master \
    file://01_skip-thread-safe-planner.patch \
    file://02_mod-tweaks.patch \
    file://zeroconvo.lv2 \
"
SRCREV="052d518ee5849f7425b89db0f791cb530100b228"

PV = "0.6.1"

S = "${WORKDIR}/git"

inherit pkgconfig

CXXFLAGS_append = " -fno-finite-math-only -DNDEBUG -D_MOD_DEVICE_DWARF"

do_install () {
    cd ${S}
    oe_runmake install DESTDIR=${D} LV2DIR=${BUNDLEDIR} PREFIX=""
    rm -rf ${D}${BUNDLEDIR}/zeroconvo.lv2/ir ${D}${BUNDLEDIR}/zeroconvo.lv2/*.ttl
    cp -r ${WORKDIR}/zeroconvo.lv2/* ${D}${BUNDLEDIR}/zeroconvo.lv2/
}

DEPENDS += " \
    lv2 \
    fftw \
    libsndfile1 \
    libsamplerate0 \
    glib-2.0 \
"

FILES_${PN} = "\
    ${LV2_DIR} \
"
