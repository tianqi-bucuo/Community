package com.cky.community.dao;

import com.cky.community.entity.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    void insert(Question question);
}
