package com.syf.util;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;

import java.io.File;

public class Mp3Util {
    public static String getDuration(File file) {
        int length = 0;
        try {
            //读取文件
            MP3File f = (MP3File) AudioFileIO.read(file);
            MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
            //获取文件时长，单位秒
            length = audioHeader.getTrackLength();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int second = length % 60;
        int minute = length / 60;
        return minute + "分" + second + "秒";
    }
}
