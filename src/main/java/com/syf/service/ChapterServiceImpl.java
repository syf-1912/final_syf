package com.syf.service;

import com.syf.dao.ChapterDao;
import com.syf.entity.Chapter;
import com.syf.util.Mp3Util;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    private static Logger logger = LoggerFactory.getLogger(ChapterServiceImpl.class);
    @Autowired
    private ChapterDao chapterDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Chapter> queryByPid(String id) {
        return chapterDao.queryByPid(id);
    }

    @Override
    public void delete(Integer id) {
        chapterDao.delete(id);
    }

    @Override
    public void save(Chapter chapter, MultipartFile audio, HttpServletRequest request) {
        //获取项目绝对路径
        String realPath = request.getServletContext().getRealPath("/");
        //指定文件上传路径
        String uploadFilePath = realPath + "audio";
        File file = new File(uploadFilePath);
        //创建文件夹存放文件
        if (!file.exists()) {
            file.mkdir();
        }
        //获取文件的原始名称
        String originalFilename = audio.getOriginalFilename();
        //获取原文件名的后缀
        String extension = FilenameUtils.getExtension(originalFilename);
        //使用UUID生成新的文件名
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //组合uuid和后缀
        String newName = uuid + "." + extension;
        //存入文件夹
        try {
            audio.transferTo(new File(uploadFilePath, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 初始化章节信息
         */
        //设置章节路径
        chapter.setAudioPath("/audio/" + newName);
        //获取章节时长
        String filePath = realPath + "audio/" + newName;
        File file1 = new File(filePath);
        String duration = Mp3Util.getDuration(file1);
        chapter.setDuration(duration);
        //获取章节大小
        Double size = Double.valueOf(audio.getSize());
        Double size1 = size / 1024 / 1024;
        /*
         * 将Double类型转换为String
         * %.2f表示保留后两位，它的处理方式是直接截掉不要的尾数，不四舍五入
         */
        String s = String.format("%.2f", size1);
        String audioSize = s + "MB";
        chapter.setSize(audioSize);
        chapterDao.save(chapter);
    }
}
