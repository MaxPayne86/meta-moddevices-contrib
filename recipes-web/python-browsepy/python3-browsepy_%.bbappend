# Recipe to install various audio files like samples, loops, midi, irs and so on.
# NOTE: actual files are stored on local filesystem under MEDIAFILES_LOCAL_DIR and are
# copied to final image in a custom sdcard image bbclass
LICENSE = "CLOSED"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI += "\
    file://LICENSE \
"

SOUNDFONTS_DIR = "/mnt/data/.soundfonts"
FILE_MANAGER_DIR = "/home/root/user-files"

do_install_append () {
    install -d ${D}/home/root

    install -d ${D}${SOUNDFONTS_DIR}
    ln -s ../..${SOUNDFONTS_DIR} ${D}/home/root/.soundfonts

    install -d ${D}${SOUNDFONTS_DIR}/gig
    install -d ${D}${SOUNDFONTS_DIR}/sf2
    install -d ${D}${SOUNDFONTS_DIR}/sfz

    cp -r ${WORKDIR}/LICENSE ${D}${SOUNDFONTS_DIR}/gig
    cp -r ${WORKDIR}/LICENSE ${D}${SOUNDFONTS_DIR}/sf2
    cp -r ${WORKDIR}/LICENSE ${D}${SOUNDFONTS_DIR}/sfz

    chmod 644 -R ${D}${SOUNDFONTS_DIR}

    # Create symlink on home/root directory for lv2 plugins
    ln -s ../..${LV2_DIR} ${D}/home/root/.lv2plugins

    # Create symlink on home/root directory for unstable lv2 plugins
    ln -s ../..${LV2_DIR_BAD} ${D}/home/root/.lv2pluginsbad

    # Create symlinks for other media files
    ln -s ../../mnt/data/.midifiles ${D}/home/root/.midifiles
    ln -s ../../mnt/data/.audiofiles ${D}/home/root/.audiofiles
    ln -s ../../mnt/data/.irfiles ${D}/home/root/.irfiles

    # Create symlinks for filemanager based on browsepy
    install -d ${D}${FILE_MANAGER_DIR}
    ln -s ../.soundfonts/sf2 ${D}${FILE_MANAGER_DIR}/'SF2 Instruments'
    ln -s ../.soundfonts/sfz/User1 ${D}${FILE_MANAGER_DIR}/'SFZ Instruments'
    ln -s ../.audiofiles/loops ${D}${FILE_MANAGER_DIR}/'Audio Loops'
    ln -s ../.audiofiles/recordings ${D}${FILE_MANAGER_DIR}/'Audio Recordings'
    ln -s ../.audiofiles/samples ${D}${FILE_MANAGER_DIR}/'Audio Samples'
    ln -s ../.audiofiles/tracks ${D}${FILE_MANAGER_DIR}/'Audio Tracks'
    ln -s ../.midifiles/clips ${D}${FILE_MANAGER_DIR}/'MIDI Clips'
    ln -s ../.midifiles/songs ${D}${FILE_MANAGER_DIR}/'MIDI Songs'
    ln -s ../.irfiles/cabs ${D}/${FILE_MANAGER_DIR}/'Speaker Cabinets IRs'
    chmod 644 -R ${D}${FILE_MANAGER_DIR}
}

FILES_${PN} += "\
    /home/root \
    ${SOUNDFONTS_DIR} \
    ${FILE_MANAGER_DIR} \
"
