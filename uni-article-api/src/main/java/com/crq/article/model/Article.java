package com.crq.article.model;

import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author crq
 * @TableName t_article
 */
@Data
@Builder
@Alias("Article")
public class Article implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 分类
     */
    private String category;

    /**
     * 作者id
     */
    private Integer userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面图
     */
    private String cover;

    /**
     * 内容
     */
    private String content;

    /**
     * 文章url
     */
    private String url;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 总字数
     */
    private String totalWords;

    /**
     * 阅读时间
     */
    private Integer duration;

    /**
     * 浏览次数
     */
    private Integer pageView;
    private String summary;

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 分类
     */
    public String getCategory() {
        return category;
    }

    /**
     * 分类
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 作者id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 作者id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 封面图
     */
    public String getCover() {
        return cover;
    }

    /**
     * 封面图
     */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 文章url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 文章url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 总字数
     */
    public String getTotalWords() {
        return totalWords;
    }

    /**
     * 总字数
     */
    public void setTotalWords(String totalWords) {
        this.totalWords = totalWords;
    }

    /**
     * 阅读时间
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * 阅读时间
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * 浏览次数
     */
    public Integer getPageView() {
        return pageView;
    }

    /**
     * 浏览次数
     */
    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Article other = (Article) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCategory() == null ? other.getCategory() == null : this.getCategory().equals(other.getCategory()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getCover() == null ? other.getCover() == null : this.getCover().equals(other.getCover()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getTotalWords() == null ? other.getTotalWords() == null : this.getTotalWords().equals(other.getTotalWords()))
            && (this.getDuration() == null ? other.getDuration() == null : this.getDuration().equals(other.getDuration()))
            && (this.getPageView() == null ? other.getPageView() == null : this.getPageView().equals(other.getPageView()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getCover() == null) ? 0 : getCover().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getTotalWords() == null) ? 0 : getTotalWords().hashCode());
        result = prime * result + ((getDuration() == null) ? 0 : getDuration().hashCode());
        result = prime * result + ((getPageView() == null) ? 0 : getPageView().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", category=").append(category);
        sb.append(", userId=").append(userId);
        sb.append(", title=").append(title);
        sb.append(", cover=").append(cover);
        sb.append(", content=").append(content);
        sb.append(", url=").append(url);
        sb.append(", createTime=").append(createTime);
        sb.append(", totalWords=").append(totalWords);
        sb.append(", duration=").append(duration);
        sb.append(", pageView=").append(pageView);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}