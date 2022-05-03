# Recipe to install mod-ui software
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

INSANE_SKIP_${PN} = "already-stripped"

S = "${WORKDIR}/git/utils"

SRC_URI = "\
    git://github.com/moddevices/mod-ui.git;protocol=https;branch=master \
    file://solve-do-package-qa-issue.patch;patchdir=../ \
"
SRCREV="6c0d2717ba041d7d65e967acfbcc148437b30bf5"

inherit pkgconfig

MOD_UI_DIR = "/usr/local/mod-ui"

do_install () {
    install -d ${D}${MOD_UI_DIR}
    cp -r ${S}/../* ${D}${MOD_UI_DIR}
    chmod 777 -R ${D}${MOD_UI_DIR}

    install -d ${D}${MOD_UI_DIR}/data
}

DEPENDS = " \
    alsa-lib \
    jack \
    lilv \
"

RDEPENDS_${PN} = "\
    bash \
    mod-host \
    sndfile-tools \
    python3 \
    python3-pillow \
    python3-pyserial \
    python3-pystache \
    python3-tornado \
    python3-pycryptodome \
    python3-aggdraw \
    python3-browsepy \
    mod-lv2-extensions \
    kxstudio-lv2-extensions \
"

FILES_${PN} += "\
    ${MOD_UI_DIR}/* \
    ${MOD_UI_DIR}/utils/libmod_utils.so \
"

