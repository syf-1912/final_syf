package com.syf.controller;

import com.syf.entity.Chapter;
import com.syf.service.ChapterService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/show")
    public List<Chapter> show(String id) {
        return chapterService.queryByPid(id);
    }

    @RequestMapping("/delete")
    private void delete(Integer id) {
        chapterService.delete(id);
    }

    @RequestMapping("/add")
    public void add(Chapter chapter, MultipartFile audio, HttpServletRequest request) {
        chapterService.save(chapter, audio, request);
    }

    @RequestMapping("/down")
    public void down(String name, String url, HttpServletRequest request, HttpServletResponse response) {
        //获取当前项目的路径
        String realPath = request.getServletContext().getRealPath("/");
        //根据url找到需要下载的文件
        String filePath = realPath + url;
        File file = new File(filePath);
        //设置响应下载的文件名
        String extension = FilenameUtils.getExtension(url);
        name = name + "." + extension;
        String a = null;
        try {
            //将文件名进行编解码
            a = new String(name.getBytes("utf-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //设置下载的文件类型，该类型为音频格式
        response.setContentType("audio/mpeg");
        //设置文件名
        response.setHeader("Content-Disposition", "attachment;filename=" + a);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            //将文件转换成字节数组使用输出流传输
            outputStream.write(FileUtils.readFileToByteArray(file));
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
