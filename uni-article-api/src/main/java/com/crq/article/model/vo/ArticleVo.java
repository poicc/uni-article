package com.crq.article.model.vo;

import com.crq.article.model.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description:
 * @author: crq
 * @create: 2022-03-29 15:46
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Alias("ArticleVo")
public class ArticleVo {
    private String id;
    private String category;
    private Integer userId;
    private String title;
    private String cover;
    private String summary;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime createTime;
    private String nickname;
    private String avatar;
    private List<Tag> tagList;
    private List<CommentVo> commentList;
}