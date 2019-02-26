package com.haohuo.util.common;

import com.aliyun.oss.common.comm.ResponseMessage;

/**
 * @ClassName BaseCompose
 * @Description TODO
 * @Author Zhang Peike
 * @Date 2019/2/24 10:39
 **/
public class BaseCompose {

    public static ComResult success(Object obj){
        return success(Constants.Ret_Ok,obj);
    }
    public static ComResult success(String respCode,Object obj){
        return success(respCode,Constants.Ret_Ok_Msg,obj);
    }
    public static ComResult success(String respCode,String respMsg,Object obj){
        return new ComResult(true,respCode,respMsg,obj);
    }


    public static ComResult error(String respCode,String respMsg){
        return error(respCode, respMsg,null);
    }
    public static ComResult error(String respCode,String respMsg,Object obj){
        return new ComResult(false,respCode,respMsg,obj);
    }

}
