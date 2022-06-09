SUMMARY = "Lv2 plugin abNinjam"
DESCRIPTION = "NINJAM client"
SECTION = "lv2/unstable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

SRC_URI = "gitsm://github.com/antanasbruzas/abNinjam.git;protocol=http \
"
SRCREV="947ba6579261d5d090d093d0b4384a832aea75bb"
SRC_URI[sha256sum] = "973d9517d775fc3b1496b6cb3987100b6cd85c09b411cd1077cb8b9134568237"

S = "${WORKDIR}/git"

EXTRA_OECMAKE = "-DABNINJAM_VST=OFF"

inherit cmake

DEPENDS += " \
    lv2 \
    freetype \
    libvorbis \
    liblo \
"

TARGET_CC_ARC2H += "-pthread"

do_install () {
    install -d ${D}/${LV2_DIR_BAD}/abNinjam.lv2
    cp -r ${WORKDIR}/build/abNinjam.lv2/*.so ${D}/${LV2_DIR_BAD}/abNinjam.lv2
    cp -r ${WORKDIR}/build/abNinjam.lv2/manifest.ttl ${D}/${LV2_DIR_BAD}/abNinjam.lv2
    cp -r ${WORKDIR}/build/abNinjam.lv2/abNinjam.ttl ${D}/${LV2_DIR_BAD}/abNinjam.lv2

    chmod 755 -R ${D}/${LV2_DIR_BAD}
}

FILES_${PN} = "\
    ${LV2_DIR_BAD} \
"
