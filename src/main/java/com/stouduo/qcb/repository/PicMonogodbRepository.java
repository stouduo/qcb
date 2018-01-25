package com.stouduo.qcb.repository;

import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;

import java.io.InputStream;

@Repository
public class PicMonogodbRepository {
    @Autowired
    private GridFsOperations gridFsOperations;

    public void delPic(String id) {
        gridFsOperations.delete(new Query().addCriteria(Criteria.where("filename").is(id)));
    }

    public GridFSDBFile getPic(String id) {
        return gridFsOperations.findOne(new Query().addCriteria(Criteria.where("filename").is(id)));
    }

    public void savePic(InputStream in, String filename) {
        gridFsOperations.store(in, filename);
    }
}
