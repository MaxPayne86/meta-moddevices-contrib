# Recipe to install screcord-lv2, a simple Lv2 capture plugin
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

INSANE_SKIP_${PN} = "ldflags"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/brkl/screcord.lv2.git;protocol=git;branch=master \
"
SRCREV="master"

S = "${WORKDIR}/git"

inherit pkgconfig

DEPENDS = " \
    libsndfile1 \
    lv2 \
"

do_install () {
    install -d ${D}/${LV2_DIR}/sc_record.lv2
    cp -r ${WORKDIR}/git/sc_record.lv2/*.so ${D}/${LV2_DIR}/sc_record.lv2
    cp -r ${WORKDIR}/git/sc_record.lv2/*.ttl ${D}/${LV2_DIR}/sc_record.lv2
    cp -r ${WORKDIR}/git/modgui ${D}/${LV2_DIR}/sc_record.lv2
}

FILES_${PN} = "\
    ${LV2_DIR}/sc_record.lv2/* \
"
