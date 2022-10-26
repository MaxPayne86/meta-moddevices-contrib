SUMMARY = "Zamaudio lv2 plugin suite"
DESCRIPTION = ""
HOMEPAGE = "http://www.zamaudio.com"
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

INSANE_SKIP:${PN} = "already-stripped"

S = "${WORKDIR}/git"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    gitsm://github.com/zamaudio/zam-plugins.git;protocol=git;branch=master \
    file://disable-generate-ttl-script.patch \
"
SRCREV="87fdee6e87dbee75c1088e2327ea59c1ab1522e4"

inherit pkgconfig

FXLIST = "\
    ZamAutoSat.lv2 \
    ZaMaximX2.lv2 \
    ZamComp.lv2 \
    ZamCompX2.lv2 \
    ZamDelay.lv2 \
    ZamEQ2.lv2 \
    ZamGate.lv2 \
    ZamGateX2.lv2 \
    ZamGEQ31.lv2 \
    ZamHeadX2.lv2 \
    ZamTube.lv2 \
    ZaMultiComp.lv2 \
    ZaMultiCompX2.lv2 \
"
# TODO: ZamDynamicEQ.lv2 ZamPhono.lv2 ZamGrains.lv2 ZamNoise.lv2 ZamPhono.lv2 ZamPiano.lv2 ZamSFZ.lv2 ZamSynth.lv2 ZamVerb.lv2

do_compile () {
    make -j 8 NOOPT=true
}

do_install () {
    # INFO: this suite now uses internal zita-convolver library
    for fx in ${FXLIST}; do
        install -d ${D}/${LV2_DIR}/${fx}
        cp -r ${S}/bin/${fx}/*.so ${D}/${LV2_DIR}/${fx}
    done
    chmod 755 -R ${D}/${LV2_DIR}/
}

DEPENDS = " \
    lilv \
    jack \
    fftw \
"

RDEPENDS:zam-plugins = "\
"

FILES:${PN} = "\
    ${LV2_DIR}/ZamAutoSat.lv2/* \
    ${LV2_DIR}/ZaMaximX2.lv2/* \
    ${LV2_DIR}/ZamComp.lv2/* \
    ${LV2_DIR}/ZamCompX2.lv2/* \
    ${LV2_DIR}/ZamDelay.lv2/* \
    ${LV2_DIR}/ZamEQ2.lv2/* \
    ${LV2_DIR}/ZamGate.lv2/* \
    ${LV2_DIR}/ZamGateX2.lv2/* \
    ${LV2_DIR}/ZamGEQ31.lv2/* \
    ${LV2_DIR}/ZamHeadX2.lv2/* \
    ${LV2_DIR}/ZamTube.lv2/* \
    ${LV2_DIR}/ZaMultiComp.lv2/* \
    ${LV2_DIR}/ZaMultiCompX2.lv2/* \
"
