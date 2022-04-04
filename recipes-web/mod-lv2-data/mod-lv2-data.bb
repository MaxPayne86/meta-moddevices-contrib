SUMMARY = "Ttl and gui files for lv2 plugins from moddevices"
DESCRIPTION = "The files contained in this repo are supposed to overwrite the ones contained in other packages if already present O_o"
SECTION = "lv2/webui"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

#INSANE_SKIP_${PN} = "already-stripped"
#INSANE_SKIP_${PN} += " installed-vs-shipped"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
    git://github.com/moddevices/mod-lv2-data.git;protocol=git;branch=master \
"
SRCREV="185049ee7fdab1f6bf35ddd6be5c8711748a3f9b"

GX_FXLIST = "\
    gxtuner.lv2 \
"

ZAM_FXLIST = "\
    ZamAutoSat.lv2 \
    ZaMaximX2.lv2 \
    ZamComp.lv2 \
    ZamCompX2.lv2 \
    ZamDelay.lv2 \
    ZamEQ2.lv2 \
    ZamGate.lv2 \
    ZamGateX2.lv2 \
    ZamGEQ31.lv2 \
    ZamHeadX2.lv2 \
    ZamTube.lv2 \
    ZaMultiComp.lv2 \
    ZaMultiCompX2.lv2 \
"

ZYN_FXLIST = "\
    ZynAddSubFX.lv2 \
    ZynAlienWah.lv2 \
    ZynChorus.lv2 \
    ZynDistortion.lv2 \
    ZynDynamicFilter.lv2 \
    ZynEcho.lv2 \
    ZynPhaser.lv2 \
    ZynReverb.lv2 \
"

DISTRHO_FXLIST1 = "\
    drowaudio-distortion.lv2 \
    drowaudio-distortionshaper.lv2 \
    drowaudio-flanger.lv2 \
    drowaudio-reverb.lv2 \
    drowaudio-tremolo.lv2 \
    Luftikus.lv2 \
    Obxd.lv2 \
    TAL-NoiseMaker.lv2 \
    TheFunction.lv2 \
    ThePilgrim.lv2 \
    Vex.lv2 \
    Wolpertinger.lv2 \
"

DISTRHO_FXLIST2 = "\
    TAL-Dub-3.lv2 \
    TAL-Filter-2.lv2 \
    TAL-Filter.lv2 \
    TAL-Reverb-2.lv2 \
    TAL-Reverb-3.lv2 \
    TAL-Reverb.lv2 \
    TAL-Vocoder-2.lv2 \
"

DPF_FXLIST = "\
    3BandEQ.lv2 \
    3BandSplitter.lv2 \
    AmplitudeImposer.lv2 \
    CycleShifter.lv2 \
    Kars.lv2 \
    MaBitcrush.lv2 \
    MaFreeverb.lv2 \
    MaGigaverb.lv2 \
    MaPitchshift.lv2 \
    MVerb.lv2 \
    Nekobi.lv2 \
    PingPongPan.lv2 \
    SoulForce.lv2 \
"

SHIRO_FXLIST = "\
    Harmless.lv2 \
    Larynx.lv2 \
    Modulay.lv2 \
    Pitchotto.lv2 \
    Shiroverb.lv2 \
"

do_install () {

    # Calf
    install -d ${D}/${LV2_DIR}/calf.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/calf.lv2/*.ttl ${D}/${LV2_DIR}/calf.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/calf.lv2/modgui ${D}/${LV2_DIR}/calf.lv2

    install -d ${D}/${LV2_DIR_BAD}/calf-bad.lv2
    cp -rfL ${WORKDIR}/git/plugins-fixed/calf-bad.lv2/*.ttl ${D}/${LV2_DIR_BAD}/calf-bad.lv2
    cp -rfL ${WORKDIR}/git/plugins-fixed/calf-bad.lv2/modgui ${D}/${LV2_DIR_BAD}/calf-bad.lv2

    # Freakclip
    install -d ${D}/${LV2_DIR}/Freakclip.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/Freakclip.lv2/*.ttl ${D}/${LV2_DIR}/Freakclip.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/Freakclip.lv2/modgui ${D}/${LV2_DIR}/Freakclip.lv2

    # Granulator
    install -d ${D}/${LV2_DIR}/Granulator.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/Granulator.lv2/*.ttl ${D}/${LV2_DIR}/Granulator.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/Granulator.lv2/modgui ${D}/${LV2_DIR}/Granulator.lv2

    # b_overdrive
    install -d ${D}/${LV2_DIR}/b_overdrive
    cp -r ${WORKDIR}/git/plugins-fixed/b_overdrive/*.ttl ${D}/${LV2_DIR}/b_overdrive
    cp -r ${WORKDIR}/git/plugins-fixed/b_overdrive/modgui ${D}/${LV2_DIR}/b_overdrive

    # b_reverb
    install -d ${D}/${LV2_DIR}/b_reverb
    cp -r ${WORKDIR}/git/plugins-fixed/b_reverb/*.ttl ${D}/${LV2_DIR}/b_reverb
    cp -r ${WORKDIR}/git/plugins-fixed/b_reverb/modgui ${D}/${LV2_DIR}/b_reverb

    # b_synth
    install -d ${D}/${LV2_DIR}/b_synth
    cp -r ${WORKDIR}/git/plugins-fixed/b_synth/*.ttl ${D}/${LV2_DIR}/b_synth
    cp -r ${WORKDIR}/git/plugins-fixed/b_synth/modgui ${D}/${LV2_DIR}/b_synth

    # b_whirl
    install -d ${D}/${LV2_DIR}/b_whirl
    cp -r ${WORKDIR}/git/plugins-fixed/b_whirl/*.ttl ${D}/${LV2_DIR}/b_whirl
    cp -r ${WORKDIR}/git/plugins-fixed/b_whirl/modgui ${D}/${LV2_DIR}/b_whirl

    # Guitarix
    for fx in ${GX_FXLIST}; do
        install -d ${D}/${LV2_DIR}/${fx}
        cp -r ${WORKDIR}/git/plugins/${fx}/*.ttl ${D}/${LV2_DIR}/${fx}
        if [ ${fx} != "gxtuner.lv2" ]; then
            cp -r ${WORKDIR}/git/plugins/${fx}/modgui ${D}/${LV2_DIR}/${fx}
        fi
    done

    # Zam Plugins
    for fx in ${ZAM_FXLIST}; do
        install -d ${D}/${LV2_DIR}/${fx}
        cp -r ${WORKDIR}/git/plugins-fixed/${fx}/*.ttl ${D}/${LV2_DIR}/${fx}
        cp -r ${WORKDIR}/git/plugins-fixed/${fx}/modgui ${D}/${LV2_DIR}/${fx}
    done

    # ZynAddSubFX
    for fx in ${ZYN_FXLIST}; do
        install -d ${D}/${LV2_DIR}/${fx}
        cp -r ${WORKDIR}/git/plugins/${fx}/*.ttl ${D}/${LV2_DIR}/${fx}
        if [ ${fx} = "ZynAddSubFX.lv2" ]; then
            cp -r ${WORKDIR}/git/plugins/${fx}/modgui ${D}/${LV2_DIR}/${fx}
        fi
    done

    # distrho-ports
    for fx in ${DISTRHO_FXLIST1}; do
        install -d ${D}/${LV2_DIR}/${fx}
        cp -r ${WORKDIR}/git/plugins/${fx}/*.ttl ${D}/${LV2_DIR}/${fx}
    done

    for fx in ${DISTRHO_FXLIST2}; do
        install -d ${D}/${LV2_DIR}/${fx}
        cp -r ${WORKDIR}/git/plugins/${fx}/*.ttl ${D}/${LV2_DIR}/${fx}
        cp -r ${WORKDIR}/git/plugins/${fx}/modgui ${D}/${LV2_DIR}/${fx}
    done

    # dpf-plugins
    for fx in ${DPF_FXLIST}; do
        install -d ${D}/${LV2_DIR}/${fx}
        if [ ${fx} = "Kars.lv2" ] || [ ${fx} = "MVerb.lv2" ] || [ ${fx} = "Nekobi.lv2" ] || [ ${fx} = "PingPongPan.lv2" ]; then
            cp -r ${WORKDIR}/git/plugins-fixed/${fx}/*.ttl ${D}/${LV2_DIR}/${fx}
            cp -r ${WORKDIR}/git/plugins-fixed/${fx}/modgui ${D}/${LV2_DIR}/${fx}
        else
            cp -r ${WORKDIR}/git/plugins/${fx}/*.ttl ${D}/${LV2_DIR}/${fx}
        fi
    done

    # shiro-plugins
    for fx in ${SHIRO_FXLIST}; do
        install -d ${D}/${LV2_DIR}/${fx}
        cp -r ${WORKDIR}/git/plugins-fixed/${fx}/*.ttl ${D}/${LV2_DIR}/${fx}
        cp -r ${WORKDIR}/git/plugins-fixed/${fx}/modgui ${D}/${LV2_DIR}/${fx}
    done

    # x42-stepseq
    install -d ${D}/${LV2_DIR}/stepseq_s8n4.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/stepseq_s8n4.lv2/*.ttl ${D}/${LV2_DIR}/stepseq_s8n4.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/stepseq_s8n4.lv2/modgui ${D}/${LV2_DIR}/stepseq_s8n4.lv2

    install -d ${D}/${LV2_DIR}/stepseq_s8n8.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/stepseq_s8n8.lv2/*.ttl ${D}/${LV2_DIR}/stepseq_s8n8.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/stepseq_s8n8.lv2/modgui ${D}/${LV2_DIR}/stepseq_s8n8.lv2

    install -d ${D}/${LV2_DIR}/stepseq_s8n16.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/stepseq_s8n16.lv2/*.ttl ${D}/${LV2_DIR}/stepseq_s8n16.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/stepseq_s8n16.lv2/modgui ${D}/${LV2_DIR}/stepseq_s8n16.lv2

    # drobilla fomp
    install -d ${D}/${LV2_DIR}/fomp.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/fomp.lv2/*.ttl ${D}/${LV2_DIR}/fomp.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/fomp.lv2/modgui ${D}/${LV2_DIR}/fomp.lv2

    # artyfx
    install -d ${D}/${LV2_DIR}/artyfx.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/artyfx.lv2/*.ttl ${D}/${LV2_DIR}/artyfx.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/artyfx.lv2/modgui ${D}/${LV2_DIR}/artyfx.lv2

    install -d ${D}/${LV2_DIR_BAD}/artyfx-bad.lv2
    cp -rfL ${WORKDIR}/git/plugins-fixed/artyfx-bad.lv2/*.ttl ${D}/${LV2_DIR_BAD}/artyfx-bad.lv2
    cp -rfL ${WORKDIR}/git/plugins-fixed/artyfx-bad.lv2/modgui ${D}/${LV2_DIR_BAD}/artyfx-bad.lv2

    # invada
    install -d ${D}/${LV2_DIR}/invada.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/invada.lv2/*.ttl ${D}/${LV2_DIR}/invada.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/invada.lv2/modgui ${D}/${LV2_DIR}/invada.lv2

    install -d ${D}/${LV2_DIR_BAD}/invada-bad.lv2
    cp -rfL ${WORKDIR}/git/plugins-fixed/invada-bad.lv2/*.ttl ${D}/${LV2_DIR_BAD}/invada-bad.lv2
    cp -rfL ${WORKDIR}/git/plugins-fixed/invada-bad.lv2/modgui ${D}/${LV2_DIR_BAD}/invada-bad.lv2

    # rkr
    install -d ${D}/${LV2_DIR}/rkr.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/rkr.lv2/*.ttl ${D}/${LV2_DIR}/rkr.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/rkr.lv2/modgui ${D}/${LV2_DIR}/rkr.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/rkr.lv2/*.rvb ${D}/${LV2_DIR}/rkr.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/rkr.lv2/*.dly ${D}/${LV2_DIR}/rkr.lv2

    install -d ${D}/${LV2_DIR_BAD}/rkr-bad.lv2
    cp -rfL ${WORKDIR}/git/plugins-fixed/rkr-bad.lv2/*.ttl ${D}/${LV2_DIR_BAD}/rkr-bad.lv2
    cp -rfL ${WORKDIR}/git/plugins-fixed/rkr-bad.lv2/modgui ${D}/${LV2_DIR_BAD}/rkr-bad.lv2
    cp -rfL ${WORKDIR}/git/plugins-fixed/rkr-bad.lv2/*.rvb ${D}/${LV2_DIR_BAD}/rkr-bad.lv2
    cp -rfL ${WORKDIR}/git/plugins-fixed/rkr-bad.lv2/*.dly ${D}/${LV2_DIR_BAD}/rkr-bad.lv2

    # sooper-looper
    install -d ${D}/${LV2_DIR_BAD}/sooperlooper.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/sooperlooper.lv2/*.ttl ${D}/${LV2_DIR_BAD}/sooperlooper.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/sooperlooper.lv2/modgui ${D}/${LV2_DIR_BAD}/sooperlooper.lv2

    install -d ${D}/${LV2_DIR_BAD}/sooperlooper-2x2.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/sooperlooper-2x2.lv2/*.ttl ${D}/${LV2_DIR_BAD}/sooperlooper-2x2.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/sooperlooper-2x2.lv2/modgui ${D}/${LV2_DIR_BAD}/sooperlooper-2x2.lv2

    # amsynth
    install -d ${D}/${LV2_DIR}/amsynth.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/amsynth.lv2/*.ttl ${D}/${LV2_DIR}/amsynth.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/amsynth.lv2/modgui ${D}/${LV2_DIR}/amsynth.lv2

    # carla-files
    install -d ${D}/${LV2_DIR}/carla-files.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/carla-files.lv2/*.ttl ${D}/${LV2_DIR}/carla-files.lv2
    cp -r ${WORKDIR}/git/plugins-fixed/carla-files.lv2/modgui ${D}/${LV2_DIR}/carla-files.lv2

    chmod 755 -R ${D}/${LV2_DIR}
    chmod 755 -R ${D}/${LV2_DIR_BAD}
}

DEPENDS = " \
    calf \
    freaked \
    lilv \
    jack \
"

FILES_${PN} = "\
    ${LV2_DIR}/calf.lv2/* \
    ${LV2_DIR_BAD}/calf-bad.lv2/* \
"

FILES_${PN} += "\
    ${LV2_DIR}/Freakclip.lv2/* \
    ${LV2_DIR}/Granulator.lv2/* \
    ${LV2_DIR}/b_overdrive/* \
    ${LV2_DIR}/b_reverb/* \
    ${LV2_DIR}/b_synth/* \
    ${LV2_DIR}/b_whirl/* \
"

FILES_${PN} += "\
    ${LV2_DIR}/gxtuner.lv2/* \
"

FILES_${PN} += "\
    ${LV2_DIR}/ZamAutoSat.lv2/* \
    ${LV2_DIR}/ZaMaximX2.lv2/* \
    ${LV2_DIR}/ZamComp.lv2/* \
    ${LV2_DIR}/ZamCompX2.lv2/* \
    ${LV2_DIR}/ZamDelay.lv2/* \
    ${LV2_DIR}/ZamEQ2.lv2/* \
    ${LV2_DIR}/ZamGate.lv2/* \
    ${LV2_DIR}/ZamGateX2.lv2/* \
    ${LV2_DIR}/ZamGEQ31.lv2/* \
    ${LV2_DIR}/ZamHeadX2.lv2/* \
    ${LV2_DIR}/ZamTube.lv2/* \
    ${LV2_DIR}/ZaMultiComp.lv2/* \
    ${LV2_DIR}/ZaMultiCompX2.lv2/* \
"

FILES_${PN} += "\
    ${LV2_DIR}/ZynAddSubFX.lv2/* \
    ${LV2_DIR}/ZynAlienWah.lv2/* \
    ${LV2_DIR}/ZynChorus.lv2/* \
    ${LV2_DIR}/ZynDistortion.lv2/* \
    ${LV2_DIR}/ZynDynamicFilter.lv2/* \
    ${LV2_DIR}/ZynEcho.lv2/* \
    ${LV2_DIR}/ZynPhaser.lv2/* \
    ${LV2_DIR}/ZynReverb.lv2/* \
"

FILES_${PN} += "\
    ${LV2_DIR}/drowaudio-distortion.lv2/* \
    ${LV2_DIR}/drowaudio-distortionshaper.lv2/* \
    ${LV2_DIR}/drowaudio-flanger.lv2/* \
    ${LV2_DIR}/drowaudio-reverb.lv2/* \
    ${LV2_DIR}/drowaudio-tremolo.lv2/* \
    ${LV2_DIR}/Luftikus.lv2/* \
    ${LV2_DIR}/Obxd.lv2/* \
    ${LV2_DIR}/TAL-Dub-3.lv2/* \
    ${LV2_DIR}/TAL-Filter-2.lv2/* \
    ${LV2_DIR}/TAL-Filter.lv2/* \
    ${LV2_DIR}/TAL-NoiseMaker.lv2/* \
    ${LV2_DIR}/TAL-Reverb-2.lv2/* \
    ${LV2_DIR}/TAL-Reverb-3.lv2/* \
    ${LV2_DIR}/TAL-Reverb.lv2/* \
    ${LV2_DIR}/TAL-Vocoder-2.lv2/* \
    ${LV2_DIR}/TheFunction.lv2/* \
    ${LV2_DIR}/ThePilgrim.lv2/* \
    ${LV2_DIR}/Vex.lv2/* \
    ${LV2_DIR}/Wolpertinger.lv2/* \
"

FILES_${PN} += "\
    ${LV2_DIR}/3BandEQ.lv2/* \
    ${LV2_DIR}/3BandSplitter.lv2/* \
    ${LV2_DIR}/AmplitudeImposer.lv2/* \
    ${LV2_DIR}/CycleShifter.lv2/* \
    ${LV2_DIR}/Kars.lv2/* \
    ${LV2_DIR}/MaBitcrush.lv2/* \
    ${LV2_DIR}/MaFreeverb.lv2/* \
    ${LV2_DIR}/MaGigaverb.lv2/* \
    ${LV2_DIR}/MaPitchshift.lv2/* \
    ${LV2_DIR}/MVerb.lv2/* \
    ${LV2_DIR}/Nekobi.lv2/* \
    ${LV2_DIR}/PingPongPan.lv2/* \
    ${LV2_DIR}/SoulForce.lv2/* \
"

FILES_${PN} += "\
    ${LV2_DIR}/Harmless.lv2/* \
    ${LV2_DIR}/Larynx.lv2/* \
    ${LV2_DIR}/Modulay.lv2/* \
    ${LV2_DIR}/Pitchotto.lv2/* \
    ${LV2_DIR}/Shiroverb.lv2/* \
"

FILES_${PN} += "\
    ${LV2_DIR}/stepseq_s8n4.lv2/* \
    ${LV2_DIR}/stepseq_s8n8.lv2/* \
    ${LV2_DIR}/stepseq_s8n16.lv2/* \
"

FILES_${PN} += "\
    ${LV2_DIR}/fomp.lv2 \
"

FILES_${PN} += "\
    ${LV2_DIR}/artyfx.lv2 \
    ${LV2_DIR_BAD}/artyfx-bad.lv2 \
"

FILES_${PN} += "\
    ${LV2_DIR}/invada.lv2 \
    ${LV2_DIR_BAD}/invada-bad.lv2 \
"

FILES_${PN} += "\
    ${LV2_DIR_BAD}/sooperlooper.lv2 \
    ${LV2_DIR_BAD}/sooperlooper-2x2.lv2 \
"

FILES_${PN} += "\
    ${LV2_DIR}/rkr.lv2 \
    ${LV2_DIR_BAD}/rkr-bad.lv2 \
"

FILES_${PN} += "\
    ${LV2_DIR}/amsynth.lv2 \
"

FILES_${PN} += "\
    ${LV2_DIR}/carla-files.lv2 \
"
