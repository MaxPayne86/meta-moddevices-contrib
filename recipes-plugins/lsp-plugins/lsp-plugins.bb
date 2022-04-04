# Recipe to install LSP (Linux Studio Plugins)
SUMMARY = "LSP (Linux Studio Plugins)"
DESCRIPTION = "LSP (Linux Studio Plugins) is a collection of open-source plugins currently compatible with LADSPA, LV2 and LinuxVST formats."
SECTION = "lv2/unstable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

INSANE_SKIP_${PN} = "ldflags"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    gitsm://github.com/sadko4u/lsp-plugins.git;protocol=git;branch=master \
    file://change-lv2path-behaviour.patch \
    file://ttl.tar.gz \
"
SRCREV="ad2720345ce5dffb45f871146de1ae6d16f4c73d"

S = "${WORKDIR}/git"

inherit pkgconfig

EXTRA_OEMAKE = "BUILD_MODULES=lv2 LV2_UI=0 BUILD_PLATFORM=Linux BUILD_PROFILE=aarch64 BUILD_R3D_BACKENDS= XLIB_HEADERS= XLIB_LIBS="

do_compile () {
    oe_runmake
}

do_install () {
    # Not possible to use make install since lv2_genttl.exe is compiled for target as well
    # so I've generated ttl files from a desktop install and provide them in a tar.gz
    #oe_runmake install DESTDIR=${D} PREFIX= LV2PATH=${LV2_DIR_BAD}

    # Install binary
    install -d ${D}/${LV2_DIR_BAD}/${PN}.lv2
    cp ${S}/.build/lsp-plugins-lv2.so ${D}/${LV2_DIR_BAD}/${PN}.lv2/

    # Install .ttl
    cp ${WORKDIR}/ttl/*.ttl ${D}/${LV2_DIR_BAD}/${PN}.lv2/

    chmod 755 -R ${D}/${LV2_DIR_BAD}
}

DEPENDS += " \
    jack \
    lv2 \
    fftw \
    libsndfile1 \
    libsamplerate0 \
"

FILES_${PN} = "\
    ${LV2_DIR_BAD} \
"
