SUMMARY = "Lv2 plugin bundle aidadsp-lv2"
DESCRIPTION = ""
URL = "https://github.com/AidaDSP/aidadsp-lv2.git"
SECTION = "lv2/unstable"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"
BUNDLEDIR = "${@bb.utils.contains('SECTION', 'lv2/stable', '${LV2_DIR}', '${LV2_DIR_BAD}', d)}"

INSANE_SKIP_${PN} += "already-stripped"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    gitsm://github.com/AidaDSP/aidadsp-lv2.git;protocol=https;branch=next \
"
SRCREV="next"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

# -mtune=cortex-a53 -mfpu=neon-fp-armv8 -mfloat-abi=hard -mvectorize-with-neon-quad
#CXXFLAGS_append_aarch64 = " -O3 -ffast-math -fno-finite-math-only -fprefetch-loop-arrays -funroll-loops -funsafe-loop-optimizations -static-libstdc++ -Wl,-Ofast -Wl,--as-needed -Wl,--strip-all"
#LDFLAGS_append_aarch64 = " -static-libstdc++ -Wl,-Ofast -Wl,--as-needed -Wl,--strip-all"

EXTRA_OECMAKE = '-DCMAKE_BUILD_TYPE=Release -DRTNEURAL_XSIMD=ON -DRTNEURAL_DEFAULT_ALIGNMENT:STRING="16" -DDESTDIR=${BUNDLEDIR}'

DEPENDS = " \
    lilv \
"

FILES_${PN} = "\
    ${BUNDLEDIR} \
"
