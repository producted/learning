package com.haohuo.transactional.service;

import com.haohuo.beans.Account;
import com.haohuo.beans.Trans;
import com.haohuo.transactional.dao.AccountMapper;
import com.haohuo.transactional.dao.TransMapper;
import com.haohuo.transactional.dao.UserBeanMapper;
import com.haohuo.util.common.BaseCompose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName TransServiceImpl
 * @Description TODO
 * @Author Zhang Peike
 * @Date 2019/2/24 10:35
 **/
@Service
public class TransServiceImpl extends BaseCompose implements TransService {

    @Autowired
    private TransMapper transMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private UserBeanMapper userMapper;

    @Autowired
    private DataManagerService dataManagerService;


    @Override
    public Object transTest() {
        Trans trans = new Trans();
        trans.setOrState(0);//支付中
        int i = transMapper.insertSelective(trans);

        try {
            dataManagerService.compose(trans.getOrId());
        }catch (Exception e){
            e.printStackTrace();//打印失败原因
            System.out.println(e.getMessage());
            trans.setOrState(2);//失败
            trans.setOrId(trans.getOrId());
            transMapper.updateByPrimaryKey(trans);
            return error("01","充值失败");
        }
        return success(null);
    }

    //充值
    public void pay(){
        Account account = new Account();
        account.setAcName("充值测试");
        account.setAcFee(5000000);//分
        account.setAcTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        int editNum = accountMapper.insertSelective(account);
        if (editNum >= 0){
            throw new RuntimeException("充值到钱包时异常");
        }
    }

    //多处调用 故提取
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
