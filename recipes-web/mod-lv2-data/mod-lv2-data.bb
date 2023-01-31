SUMMARY = "Ttl and web gui files for lv2 plugins from moddevices"
DESCRIPTION = "These files usually overwrite those present in plugins sources"
SECTION = "lv2/webui"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/moddevices/mod-lv2-data.git;protocol=https;branch=master \
"
SRCREV="031517248a96dac83a0da898ec6d0321895d707b"
