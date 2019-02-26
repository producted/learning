package com.haohuo.transactional.dao;

import com.haohuo.beans.Trans;

public interface TransMapper {
    int deleteByPrimaryKey(Integer orId);

    int insert(Trans record);

    int insertSelective(Trans record);

    Trans selectByPrimaryKey(Integer orId);

    int updateByPrimaryKeySelective(Trans record);

    int updateByPrimaryKey(Trans record);
}