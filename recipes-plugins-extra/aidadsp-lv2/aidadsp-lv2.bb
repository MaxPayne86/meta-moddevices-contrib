SUMMARY = "Lv2 plugin bundle aidadsp-lv2"
DESCRIPTION = ""
URL = "https://github.com/AidaDSP/aidadsp-lv2.git"
SECTION = "lv2/stable"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"
BUNDLEDIR = "${@bb.utils.contains('SECTION', 'lv2/stable', '${LV2_DIR}', '${LV2_DIR_BAD}', d)}"

INSANE_SKIP_${PN} += "already-stripped"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    gitsm://github.com/AidaDSP/aidadsp-lv2.git;protocol=https;branch=main \
"
SRCREV="0f97944ffe91811293fdc2811f684450b6b30e14"

PV = "0.95"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

# -funsafe-loop-optimizations causes the plugin to sound really bad, excluding
CXXFLAGS_append_aarch64 = " -fprefetch-loop-arrays -funroll-loops -static-libstdc++ -Wl,-Ofast -Wl,--as-needed -Wl,--strip-all"
LDFLAGS_append_aarch64 = " -static-libstdc++ -Wl,-Ofast -Wl,--as-needed -Wl,--strip-all"

EXTRA_OECMAKE = '-DCMAKE_BUILD_TYPE=Release -DRTNEURAL_XSIMD=ON -DDESTDIR=${BUNDLEDIR}'

DEPENDS = " \
    lv2 \
"

FILES_${PN} = "\
    ${BUNDLEDIR} \
"
