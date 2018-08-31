package com.syf.test;

import com.syf.CmfzApp;
import com.syf.dao.AlbumDao;
import com.syf.entity.Album;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = CmfzApp.class)
public class TestAlbum {
    @Autowired
    private AlbumDao albumDao;

    @Test
    public void test1() {
        List<Album> albums = albumDao.queryAll(0, 2);
        for (Album album : albums) {
            System.out.println(album);
        }
    }
}
