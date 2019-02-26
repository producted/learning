package com.haohuo.beans;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangpeike
 * @date 10:53 2019/1/21
 */
@Data
public class User implements Serializable {

    private Integer id;
    private Integer age;
    private String name;
    private String remark;
    private String sex;
    private String loginNum;
    private String pwd;
}
