package com.crq.article.service;

import com.crq.article.model.Tag;

import java.util.List;

/**
 * @description:
 * @author: crq
 * @create: 2022-03-29 14:53
 **/
public interface TagService {
    /**
     * 查询所有tag
     * @return tag
     */
    List<Tag> selectAll();

}
