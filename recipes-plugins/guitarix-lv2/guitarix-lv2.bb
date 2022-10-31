# INFO: guitarix uses fftw single precision calls in some plugins,
# while for other plugins using zita-convolver a version based on ffmpeg instead of fftw is used
# LICENSE: the web graphics of gx_shimmizita.lv2 is provided by lv2-data-creative-commons
SUMMARY = "Guitarix lv2 plugins"
DESCRIPTION = "Recipe to install lv2 plugins contained in Guitarix"
HOMEPAGE = "http://guitarix.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=384f45fb7968a0fe30622ce6160d3b69"

SRC_URI = "\
    git://github.com/brummer10/guitarix.git;protocol=https;branch=master \
    file://0001-Rework-messages-somehow-yes-or-no-is-missing.patch \
    file://0002-Do-not-strip-LV2-plugins.patch \
    file://0003-Use-avail-libgmm-version.patch \
"
SRCREV = "a14b455d4d258917a597456c674b6f80470e1d80"
PV = "0.42.1"
S = "${WORKDIR}/git/trunk"

inherit waf pkgconfig

EXTRA_OECONF = " \
    --disable-sse \
    --ldflags="${LDFLAGS}" \
    --includeresampler \
    --convolver-ffmpeg \
    --no-ldconfig \
    --destdir=${D} \
    --no-standalone \
    --no-desktop-update \
    --no-faust \
    --no-lv2-gui \
    --mod-lv2 \
    --lv2dir=${LV2_DIR} \
"

do_install_append () {
    # gxtuner.lv2 graphics is installed by mod-lv2-data recipe
    rm -rf ${D}${LV2_DIR}/gxtuner.lv2/*.ttl

    # gx_shimmizita.lv2 graphics is installed by lv2-data-creative-commons recipe
    rm -rf ${D}${LV2_DIR}/gx_shimmizita.lv2/*.ttl
    rm -rf ${D}${LV2_DIR}/gx_shimmizita.lv2/modgui
}

DEPENDS = " \
    zita-convolver \
    zita-resampler \
    fftw \
    ffmpeg \
    glibmm \
    libeigen \
    lilv \
    jack \
"

FILES_${PN} += "\
    ${LV2_DIR} \
"
