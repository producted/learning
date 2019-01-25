package com.haohuo.filter.controller;

import com.haohuo.beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author Zhang Peike
 * @Date 2019/1/24 10:46
 **/
@RestController
public class LoginController {

    /**
     * @Description: 假设root为账号/密码
     * @Param:
     * @Author: zhangpk
     */
    @RequestMapping("/login")
    public ModelAndView login(User user, HttpServletRequest request){
        HttpSession session = request.getSession();
        if (user.getLoginNum().equals("root") && user.getLoginNum().equals("root"))
        {
            session.setAttribute("user",user);
            return new ModelAndView("orther");
        }else {
            return new ModelAndView("login");
        }
    }

}
