package com.crq.article.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author crq
 * @TableName t_comment
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    private Integer id;
    private Integer articleId;
    private Integer userId;
    private String content;
    private LocalDateTime createTime;
}