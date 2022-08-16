# INFO: The plugins in mod-cv-plugins use mod:CVPort specifier in the ttl.
SUMMARY = "Ttl and gui files for lv2 plugins from moddevices with cc license"
DESCRIPTION = "The files contained in this repo are supposed to overwrite the ones contained in other packages if already present O_o"
SECTION = "lv2/webui"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

#INSANE_SKIP_${PN} = "already-stripped"
#INSANE_SKIP_${PN} += " installed-vs-shipped"
do_compile[noexec] = "1"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/moddevices/lv2-data-creative-commons.git;protocol=git;branch=master \
"
SRCREV="1143b9e85ad74312fc9daac2d3897b068e4d06d5"

S = "${WORKDIR}/git"

MODCV_FXLIST = "\
    mod-audio-to-cv \
    mod-cv-abs \
    mod-cv-attenuverter \
    mod-cv-clock \
    mod-cv-control \
    mod-cv-gate \
    mod-cv-meter \
    mod-cv-parameter-modulation \
    mod-cv-random \
    mod-cv-range \
    mod-cv-round \
    mod-cv-slew \
    mod-cv-switch1 \
    mod-cv-switch2 \
    mod-cv-switch3 \
    mod-cv-switch4 \
    mod-cv-to-audio \
    mod-logic-operators \
    mod-midi-to-cv-mono \
    mod-midi-to-cv-poly \
"

do_install () {
    # gx_shimmizita.lv2 (guitarix-lv2)
    install -d ${D}/${LV2_DIR}/gx_shimmizita.lv2
    cp -r ${WORKDIR}/git/plugin-data/gx_shimmizita.lv2/*.ttl ${D}/${LV2_DIR}/gx_shimmizita.lv2
    cp -r ${WORKDIR}/git/plugin-data/gx_shimmizita.lv2/modgui ${D}/${LV2_DIR}/gx_shimmizita.lv2

    # gx_supersonic.lv2 (gxplugins)
    install -d ${D}/${LV2_DIR}/GxSupersonic.lv2
    cp -r ${WORKDIR}/git/plugin-data/gx_supersonic.lv2/*.ttl ${D}/${LV2_DIR}/GxSupersonic.lv2
    cp -r ${WORKDIR}/git/plugin-data/gx_supersonic.lv2/modgui ${D}/${LV2_DIR}/GxSupersonic.lv2

    # mod-cv-plugins
    for fx in ${MODCV_FXLIST}; do
        install -d ${D}/${LV2_DIR}/${fx}.lv2
        cp -r ${WORKDIR}/git/plugin-data/mod-cv-plugins/${fx}.lv2/*.ttl ${D}/${LV2_DIR}/${fx}.lv2
        cp -r ${WORKDIR}/git/plugin-data/mod-cv-plugins/${fx}.lv2/modgui ${D}/${LV2_DIR}/${fx}.lv2
    done

    # distrho-a-fluidsynth.lv2 (die-plugins)
    install -d ${D}/${LV2_DIR}/distrho-a-fluidsynth.lv2
    cp -r ${WORKDIR}/git/plugin-data/distrho-a-fluidsynth.lv2/*.ttl ${D}/${LV2_DIR}/distrho-a-fluidsynth.lv2
    cp -r ${WORKDIR}/git/plugin-data/distrho-a-fluidsynth.lv2/modgui ${D}/${LV2_DIR}/distrho-a-fluidsynth.lv2

    chmod 755 -R ${D}/${LV2_DIR}
}

DEPENDS = " \
"

FILES_${PN} = "\
    ${LV2_DIR} \
"
