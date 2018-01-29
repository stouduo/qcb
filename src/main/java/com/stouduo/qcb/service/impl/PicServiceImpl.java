package com.stouduo.qcb.service.impl;

import com.mongodb.gridfs.GridFSDBFile;
import com.stouduo.qcb.domain.Picture;
import com.stouduo.qcb.repository.PicMonogodbRepository;
import com.stouduo.qcb.repository.PicRepository;
import com.stouduo.qcb.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
public class PicServiceImpl implements PicService {
    @Autowired
    private PicRepository picRepository;
    @Autowired
    private PicMonogodbRepository picMonogodbRepository;
    private final static String PIC_REST_API_PRE = "/pic/";
    @Value("${img.server.url:http://localhost:9090}")
    private String imgServerUrl;

    @Override
    public List<Picture> getPics(int pageSize, int curPage) {
        return picRepository.findAll(new PageRequest(curPage, pageSize, new Sort(Sort.Direction.DESC, "createTime"))).getContent();
    }

    @Override
    public void delPic(String ids) {
        String[] idArr = Pattern.compile(",")
                .splitAsStream(ids)
                .toArray(String[]::new);
        for (String id : idArr) {
            picRepository.delete(id);
            picMonogodbRepository.delPic(id);
        }
    }

    @Override
    public void uploadPic(MultipartFile file) throws IOException {
        Picture picture = new Picture();
        picture.setCreateTime(new Date());
        picture.setName(file.getName());
        picture.setSize(file.getSize());
        picture = picRepository.save(picture);
        picture.setSrc(imgServerUrl + PIC_REST_API_PRE + picture.getId());
        picRepository.save(picture);
        picMonogodbRepository.savePic(file.getInputStream(), picture.getId());
    }

    @Override
    public GridFSDBFile getPic(String id) {
        return picMonogodbRepository.getPic(id);
    }

}
