package com.syf.service;

import com.syf.dao.AlbumDao;
import com.syf.entity.Album;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    private static Logger logger = LoggerFactory.getLogger(AlbumServiceImpl.class);
    @Autowired
    private AlbumDao albumDao;

    @Override
    public void save(Album album, MultipartFile img, HttpServletRequest request) {
        //获取项目绝对路径
        String realPath = request.getServletContext().getRealPath("/");
        //指定文件上传路径
        String uploadFilePath = realPath + "upload";
        File file = new File(uploadFilePath);
        //创建文件夹存放文件
        if (!file.exists()) {
            file.mkdir();
        }
        //获取文件的原始名称
        String originalFilename = img.getOriginalFilename();
        //使用UUID生成新的文件名
        String uuid = UUID.randomUUID().toString().replace("-", "");
        logger.debug(uuid);
        //获取原文件名的后缀
        String extension = FilenameUtils.getExtension(originalFilename);
        //组合uuid和后缀
        String newName = uuid + "." + extension;
        //存入文件夹
        try {
            img.transferTo(new File(uploadFilePath, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //初始化专辑信息
        //设置专辑ID
        String id = UUID.randomUUID().toString().replace("-", "");
        album.setId(id);
        //创建时间
        album.setCreateDate(new Date());
        //封面路径
        album.setCorverImg("/upload/" + newName);
        albumDao.save(album);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Album> queryAll(Integer page, Integer rows) {
        Integer i = (page - 1) * rows;
        List<Album> albums = albumDao.queryAll(i, rows);
        return albums;
    }

    @Override
    public void delete(String id) {
        albumDao.delete(id);
    }

    @Override
    public Integer total() {
        return albumDao.total();
    }
}
