SUMMARY = "Lv2 plugin abNinjam"
DESCRIPTION = "NINJAM client"
SECTION = "lv2/unstable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

SRC_URI = " \
    gitsm://github.com/antanasbruzas/abNinjam.git;protocol=https \
    file://connection.properties \
"
SRCREV="79e1ee00c71e3219fa5b5c807e7cf6d186ca6a2c"

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
    install -d ${D}/${ROOT_HOME}/abNinjam
    install -m 0644 ${WORKDIR}/connection.properties ${D}/${ROOT_HOME}/abNinjam/
}

FILES_${PN} = "\
    ${LV2_DIR_BAD} \
    ${ROOT_HOME}/abNinjam \
"
