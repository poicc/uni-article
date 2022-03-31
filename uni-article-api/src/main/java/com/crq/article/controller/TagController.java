package com.crq.article.controller;

import com.crq.article.model.Tag;
import com.crq.article.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: crq
 * @create: 2022-03-29 14:57
 **/
@RestController
@RequestMapping(value = "/api/v1/tags")
@Slf4j
public class TagController {
    @Resource
    private TagService tagService;

    @GetMapping("all")
    public List<Tag> getTags() {
        return tagService.selectAll();
    }
}
