package com.haohuo.transactional.service;

import com.haohuo.beans.Account;
import com.haohuo.beans.Trans;
import com.haohuo.transactional.dao.AccountMapper;
import com.haohuo.transactional.dao.TransMapper;
import com.haohuo.transactional.dao.UserBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DataManagerServiceImpl
 * @Description TODO
 * @Author Zhang Peike
 * @Date 2019/2/25 9:08
 **/
@Service
public class DataManagerServiceImpl implements DataManagerService {

    @Autowired
    private TransMapper transMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private UserBeanMapper userMapper;

    @Override
    @Transactional
    public void compose(Integer orId){
        pay();
        updateState(orId);
    }

    //充值
    @Transactional
    public void pay(){
        Account account = new Account();
        account.setAcName("充值测试");
        account.setAcFee(5000000);//分
        account.setAcTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        int editNum = accountMapper.insertSelective(account);
        if (editNum < 1){
            throw new RuntimeException("充值到钱包时异常");
        }
    }

    //多处调用 故提取
    @Transactional
    public void updateState(Integer orId){
        Trans trans = new Trans();
        trans.setOrState(1);
        trans.setOrId(orId);
        int i = transMapper.updateByPrimaryKey(trans);
        //故意让其回滚
        if (i >= 1)
            throw new RuntimeException("修改状态时异常");
    }
}
