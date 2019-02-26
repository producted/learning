package com.haohuo.transactional.dao;

import com.haohuo.beans.UserBean;

public interface UserBeanMapper {
    int deleteByPrimaryKey(Long tId);

    int insert(UserBean record);

    int insertSelective(UserBean record);

    UserBean selectByPrimaryKey(Long tId);

    int updateByPrimaryKeySelective(UserBean record);

    int updateByPrimaryKey(UserBean record);
}