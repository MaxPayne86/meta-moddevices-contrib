SUMMARY = "Lv2 plugin suite Cardinal"
DESCRIPTION = ""
SECTION = "lv2/unstable"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

INSANE_SKIP_${PN} += "already-stripped"

PV = "22.05"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    https://github.com/DISTRHO/Cardinal/releases/download/${PV}/cardinal+deps-${PV}.tar.xz \
    file://0001-Removed-quickjs-dep-needed-by-Aria-Modules.patch \
    file://0002-Removed-Aria-Modules.patch \
    file://0003-fix-gcc_struct-error.patch \
    file://0004-remove-quickjs-dep-from-cardinal.patch \
    file://0005-remove-static-Aria-Modules-instances.patch \
    file://0006-fix-non-present-modgui-graphics.patch \
    file://cardinal-ttl-files.tar.gz \
    file://manifest.ttl \
    file://CardinalFX.ttl \
"
SRC_URI[md5sum] = "0ee33a89f00b8a31835513391b24a840"
SRC_URI[sha256sum] = "6f823170b098e612dce93b391bb67ee67dfc16ce0017104f92e3d8b011292ebd"

S = "${WORKDIR}/cardinal-${PV}"

inherit pkgconfig

CFLAGS_append = " -ffat-lto-objects -Wno-format-security -Wimplicit-fallthrough=0"
CXXFLAGS_append = " -ffat-lto-objects -Wno-format-security -Wimplicit-fallthrough=0"
LDFLAGS_append = " -ffat-lto-objects -Wno-format-security -Wimplicit-fallthrough=0"
EXTRA_OEMAKE = "HEADLESS=true CROSS_COMPILING=true MOD_BUILD=true NOOPT=true SYSDEPS=true STATIC_BUILD=true WITH_LTO=true"

do_install () {
    install -d ${D}/${LV2_DIR_BAD}/CardinalFX.lv2
    cp -rL ${S}/bin/CardinalFX.lv2 ${D}/${LV2_DIR_BAD}/

    # @TODO: remove 0006-fix-non-present-modgui-graphics.patch in next Cardinal releases
    cp -r ${WORKDIR}/src/MOD/CardinalFX.lv2/* ${D}/${LV2_DIR_BAD}/CardinalFX.lv2/

    # @TODO: these ttl files came from the arm64 X11 release, fix them
    install -m 775 ${WORKDIR}/manifest.ttl ${D}/${LV2_DIR_BAD}/CardinalFX.lv2/
    install -m 775 ${WORKDIR}/CardinalFX.ttl ${D}/${LV2_DIR_BAD}/CardinalFX.lv2/

    chmod 755 -R ${D}/${LV2_DIR_BAD}
}

DEPENDS += " \
    lilv \
    lv2 \
    fftw \
    liblo \
    jansson \
    libarchive \
    libsamplerate0 \
    speexdsp \
    liblo \
"

RDEPENDS_${PN} += " \
    fftw \
    liblo \
    jansson \
    libarchive \
    libsamplerate0 \
    speexdsp \
    liblo \
"

FILES_${PN} = "\
    ${LV2_DIR_BAD} \
"