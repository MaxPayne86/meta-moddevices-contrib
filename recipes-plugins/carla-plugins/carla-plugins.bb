SUMMARY = "Carla lv2 plugins"
DESCRIPTION = "Carla lv2 plugins contains audio and midi players"
SECTION = "lv2/stable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

INSANE_SKIP_${PN} = "already-stripped"

SRC_URI = "\
    gitsm://github.com/falkTX/Carla.git;protocol=https;branch=main \
"
SRCREV="51028655d09c9a21fc51cadd7bc48210295aa791"

PV = "2.4.1"

S = "${WORKDIR}/git"

inherit pkgconfig

CARLA_PLUGINS_NO_GUI = 'HAVE_FFMPEG=false HAVE_HYLIA=false HAVE_PYQT=false'

do_compile () {
    oe_runmake ${CARLA_PLUGINS_NO_GUI} NOOPT=true lv2-bundles
}

do_install () {
    # carla-files.lv2 graphics is installed by mod-lv2-data recipe
    install -d ${D}${LV2_DIR}/carla-files.lv2
    cp -rL ${S}/bin/carla-files.lv2/*.so ${D}${LV2_DIR}/carla-files.lv2
}

DEPENDS += " \
    lv2 \
    jack \
    libsndfile1 \
"

FILES_${PN} = "\
    ${LV2_DIR} \
"
