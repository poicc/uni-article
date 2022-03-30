package com.crq.article.service.impl;

import com.crq.article.mapper.TagMapper;
import com.crq.article.model.Tag;
import com.crq.article.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: crq
 * @create: 2022-03-29 14:55
 **/
@Service
public class TagServiceImpl implements TagService {
    @Resource
    private TagMapper tagMapper;


    @Override
    public List<Tag> selectAll() {
        return tagMapper.selectAll();
    }
}
