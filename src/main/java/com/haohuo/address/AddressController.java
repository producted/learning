package com.haohuo.address;

import com.alibaba.fastjson.JSONObject;
import com.haohuo.util.HttpUtil;
import com.haohuo.util.IpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AddressController
 * @Description TODO
 * @Author Zhang Peike
 * @Date 2019/1/29 15:52
 **/
@RestController
@RequestMapping(value = "address")
public class AddressController {

    //百度ak
    private static String AK = "Ou5eHsyBnPzLtkDmYQ5kfyGNlKHX6iHK";

    @Value("${baidu.address.one}")
    StringBuffer baiduExetendUrl;

    @Value("${baidu.address.two}")
    StringBuffer baiduIpUrl;

    /**
     * @Description: 简单调用
     * @Param:
     * @Author: zhangpk
     */
    @PostMapping(value = "getAddInfo")
    Object getAddInfo(@RequestParam String info){
        //获取IP
        String ip = IpUtil.getIp();
        String province;

        baiduIpUrl.append("?ip=" + ip)
                    .append("&ak=" + AK);
        String s = HttpUtil.httpGet(baiduIpUrl.toString());
        JSONObject object = JSONObject.parseObject(s);
        province = object.getJSONObject("content").getJSONObject("address_detail").getString("province");

        baiduExetendUrl.append("?q=" + info)
                        .append("&region="+province)
                        .append("&output=json")
                        .append("&ak="+AK);
        String addInfo = HttpUtil.httpGet(baiduExetendUrl.toString());
        System.out.println(addInfo);
        return addInfo;
    }
}
