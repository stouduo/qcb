package com.stouduo.qcb.service;

import com.mongodb.gridfs.GridFSDBFile;
import com.stouduo.qcb.domain.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PicService {
    List<Picture> getPics(int pageSize, int curPage);

    void delPic(String ids);

    void uploadPic(MultipartFile file) throws IOException;

    GridFSDBFile getPic(String id);
}
