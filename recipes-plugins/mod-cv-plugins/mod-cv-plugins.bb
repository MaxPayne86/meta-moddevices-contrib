# INFO: the plugins here use mod:CVPort specifier in the ttl
# LICENSE: the web graphics is provided by lv2-data-creative-commons
SUMMARY = "Mod-cv-plugins plugin suite"
DESCRIPTION = ""
SECTION = "lv2/unstable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    gitsm://github.com/moddevices/mod-cv-plugins.git;protocol=git;branch=master \
    file://remove-mod-cv-ports.patch \
"
SRCREV="0fe6574bb4178ecb7a294212d0449b2af039e713"

S = "${WORKDIR}/git"

FXLIST = "\
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

inherit pkgconfig

do_compile () {
    oe_runmake
}

do_install () {
    install -d ${D}${LV2_DIR_BAD}
    #oe_runmake install DEST_DIR=${D}${LV2_DIR_BAD}
    for fx in ${FXLIST}; do
        install -d ${D}/${LV2_DIR_BAD}/${fx}.lv2
        if [ ${fx} = "mod-logic-operators" ]; then
            cp -r ${S}/source/${fx}/bin/${fx}.lv2/*.so ${D}/${LV2_DIR_BAD}/${fx}.lv2
        else
            cp -r ${S}/source/${fx}/${fx}.lv2/*.so ${D}/${LV2_DIR_BAD}/${fx}.lv2
        fi
    done
    chmod 755 -R ${D}/${LV2_DIR_BAD}
}

DEPENDS += " \
    lv2 \
"

FILES_${PN} = "\
    ${LV2_DIR_BAD} \
"
