SUMMARY = "Real-time neural network inferencing"
URL = "https://github.com/jatinchowdhury18/RTNeural"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=989b379cee495d4160172fe4c2033ea4"

DEPENDS = "xsimd"

SRC_URI = "\
    git://github.com/jatinchowdhury18/RTNeural.git;protocol=https;branch=main \
"

SRCREV="a32024ba96ee1cd37be6c71188b57dec8e3cbbbe"

S = "${WORKDIR}/git"

EXTRA_OECMAKE = "-DRTNEURAL_XSIMD=ON"

inherit cmake pkgconfig

do_install () {
    install -d ${D}${libdir}
    install -m 644 ${B}/RTNeural/libRTNeural.a ${D}${libdir}/libRTNeural.a
}

FILES_${PN} += "${libdir}"
FILES_${PN}-dbg += "${libdir}/.debug"
