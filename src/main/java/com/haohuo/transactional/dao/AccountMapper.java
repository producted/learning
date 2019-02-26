package com.haohuo.transactional.dao;

import com.haohuo.beans.Account;

public interface AccountMapper {
    int deleteByPrimaryKey(Integer acId);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer acId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}