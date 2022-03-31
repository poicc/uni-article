package com.crq.article.model;

import com.crq.article.model.vo.CommentVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 
 * @author crq
 * @TableName t_article
 */
@Data
@Builder
@Alias("Article")
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    private Integer id;
    private String category;
    private Integer userId;
    private String title;
    private String cover;
    private String summary;
    private String content;
    private String url;
    private String totalWords;
    private Integer duration;
    private String pageView;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime createTime;
    private List<ArticleTag> tagList;
    private List<CommentVo> commentList;


    @Serial
    private static final long serialVersionUID = 1L;

}