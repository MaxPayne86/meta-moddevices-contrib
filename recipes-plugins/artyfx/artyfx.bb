SUMMARY = "Artyfx OpenAV lv2 plugin suite"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/openAVproductions/openAV-ArtyFX.git;protocol=git;branch=master \
    file://01_build-flags.patch \
"
SRCREV="3fdeecd081c9a03b3a630d2ed0825674a30c05d5"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

EXTRA_OECMAKE = " -DBUILD_GUI=OFF"

do_compile () {
    oe_runmake
}

do_install () {
    install -d ${D}/${LV2_DIR}/artyfx.lv2
    cp -r ${WORKDIR}/build/artyfx.so ${D}/${LV2_DIR}/artyfx.lv2
    chmod 755 -R ${D}/${LV2_DIR}/artyfx.lv2

    install -d ${D}/${LV2_DIR_BAD}/artyfx-bad.lv2
    cp -r ${WORKDIR}/build/artyfx.so ${D}/${LV2_DIR_BAD}/artyfx-bad.lv2
    chmod 755 -R ${D}/${LV2_DIR_BAD}/artyfx-bad.lv2
}

DEPENDS = "\
    lv2 \
"

RDEPENDS_${PN} = "\
"

FILES_${PN} = "\
    ${LV2_DIR}/artyfx.lv2 \
    ${LV2_DIR_BAD}/artyfx-bad.lv2 \
"
