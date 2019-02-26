package com.haohuo.util.common;

import com.sun.corba.se.spi.ior.ObjectId;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

/**
 * @ClassName ComResult
 * @Description TODO
 * @Author Zhang Peike
 * @Date 2019/2/24 10:37
 **/
@Data
public class ComResult {

    private Boolean result;
    private String respCode;
    private String respMsg;
    private Object json;

    public ComResult(Boolean result, String respCode, String respMsg, Object json) {
        this.result = result;
        this.respCode = respCode;
        this.respMsg = respMsg;
        this.json = json;
    }
    public ComResult(){
        super();
    }
}
