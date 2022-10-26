# LICENSE: the web graphics of GxSupersonic.lv2 is provided by lv2-data-creative-commons
SUMMARY = "A set of extra lv2 plugins from the guitarix project"
DESCRIPTION = ""
HOMEPAGE = "https://github.com/brummer10/GxPlugins.lv2"
SECTION = "lv2/stable"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=84dcc94da3adb52b53ae4fa38fe49e5d"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

SRC_URI = "\
    gitsm://github.com/brummer10/GxPlugins.lv2.git;protocol=git;branch=master \
"
SRCREV = "e40b34f3fd5dc4c6523dc826062d0ddb2578f573"
PV = "0.8"
S = "${WORKDIR}/git"

FXLIST = "\
    GxAxisFace.lv2 \
    GxBaJaTubeDriver.lv2 \
    GxBlueAmp.lv2 \
    GxBoobTube.lv2 \
    GxBottleRocket.lv2 \
    GxClubDrive.lv2 \
    GxCreamMachine.lv2 \
    GxDOP250.lv2 \
    GxEpic.lv2 \
    GxEternity.lv2 \
    GxFz1b.lv2 \
    GxFz1s.lv2 \
    GxGuvnor.lv2 \
    GxHeathkit.lv2 \
    GxHotBox.lv2 \
    GxHyperion.lv2 \
    GxKnightFuzz.lv2 \
    GxLiquidDrive.lv2 \
    GxLuna.lv2 \
    GxMicroAmp.lv2 \
    GxPlexi.lv2 \
    GxQuack.lv2 \
    GxSaturator.lv2 \
    GxSD1.lv2 \
    GxSD2Lead.lv2 \
    GxShakaTube.lv2 \
    GxSloopyBlue.lv2 \
    GxSlowGear.lv2 \
    GxSunFace.lv2 \
    GxSuperFuzz.lv2 \
    GxSupersonic.lv2 \
    GxSuppaToneBender.lv2 \
    GxSVT.lv2 \
    GxTimRay.lv2 \
    GxToneMachine.lv2 \
    GxTubeDistortion.lv2 \
    GxUltraCab.lv2 \
    GxUVox720k.lv2 \
    GxValveCaster.lv2 \
    GxVBassPreAmp.lv2 \
    GxVintageFuzzMaster.lv2 \
    GxVmk2.lv2 \
    GxVoodoFuzz.lv2 \
"

do_compile () {
    for fx in ${FXLIST}; do
        cd ${fx}
        sed -i -- 's/-msse3 -mfpmath=sse//' ./Makefile
        oe_runmake nogui
        cd ..
    done
}

do_install () {
    for fx in ${FXLIST}; do
        install -d ${D}/${LV2_DIR}/${fx}
        cp -r ${S}/${fx}/*.so ${D}/${LV2_DIR}/${fx}
        if [ ${fx} != "GxSupersonic.lv2" ]; then
            cp -r ${S}/${fx}/MOD/* ${D}/${LV2_DIR}/${fx}
        fi
        chmod 755 -R ${D}/${LV2_DIR}/${fx}
    done
}

DEPENDS = " \
    lilv \
    jack \
"

FILES:${PN} = "\
    ${LV2_DIR} \
"
