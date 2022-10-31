SUMMARY = "Simple web file browser using flask"
HOMEPAGE = "https://github.com/ergoithz/browsepy"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e8d404beb09945b662eef91856df88db"

inherit pypi setuptools3

SRC_URI = "\
    git://github.com/moddevices/browsepy.git;protocol=https;branch=master \
"
SRCREV="368e6918f10175111956c89907f7baec4efe99b3"

S = "${WORKDIR}/git"

RDEPENDS_${PN} = "\
    python3-flask \
    python3-unicategories \
"
