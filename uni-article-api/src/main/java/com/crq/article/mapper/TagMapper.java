package com.crq.article.mapper;

import com.crq.article.model.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author crq
 * @Entity com.crq.article.model.Tag
 */
@Mapper
public interface TagMapper {
    /**
     * 查询所有标签
     * @return tagList
     */
    @Select("SELECT * FROM t_tag")
    List<Tag> selectAll();


}




