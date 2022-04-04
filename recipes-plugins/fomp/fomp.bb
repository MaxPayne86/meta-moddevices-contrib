SUMMARY = "Fomp lv2 plugin suite"
DESCRIPTION = ""
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://git.drobilla.net/fomp.lv2.git;protocol=git;branch=master \
    file://01_add-mod-brand-and-label.patch \
"
SRCREV="4511cfc6641bb9b4b08d63f3cd7d16911bce8372"

S = "${WORKDIR}/git"

inherit waf pkgconfig

do_configure () {
    ${S}/waf configure --prefix=${D}/usr
}

do_compile () {
    ${S}/waf build
}

do_install () {
    #${S}/waf install --destdir=${DESTDIR}
    install -d ${D}/${LV2_DIR}/fomp.lv2
    #cp -r ${S}/fomp.lv2/* ${D}/${LV2_DIR}/fomp.lv2
    cp -r ${S}/build/fomp.lv2/*.so ${D}/${LV2_DIR}/fomp.lv2
    chmod 755 -R ${D}/${LV2_DIR}/fomp.lv2
}

DEPENDS = "\
    lv2 \
"

RDEPENDS_${PN} = "\
"

FILES_${PN} = "\
    ${LV2_DIR}/fomp.lv2 \
"
