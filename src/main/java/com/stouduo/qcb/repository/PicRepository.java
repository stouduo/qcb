package com.stouduo.qcb.repository;

import com.stouduo.qcb.domain.Picture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PicRepository extends PagingAndSortingRepository<Picture, String> {
    Page<Picture> findAll(Pageable pageable);
}
