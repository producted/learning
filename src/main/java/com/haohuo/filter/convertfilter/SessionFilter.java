package com.haohuo.filter.convertfilter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName ConvertFilter
 * @Description TODO
 * @Author Zhang Peike
 * @Date 2019/1/24 10:53
 **/
@Component
@WebFilter(filterName = "sessionFilter", urlPatterns = "/*")
public class SessionFilter  implements Filter {

    private static Logger logger = LoggerFactory.getLogger(SessionFilter.class);

    //未登录标识
    private String NO_LOGIN="未登录";

    private String[] includeUrls = new String[]{"/login","register",};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession(false);

        String uri = request.getRequestURI();
        logger.info("filter url:" + uri);

        boolean isNeed = isNeedFilter(uri);
        if (!isNeed){//不需要过滤直接传给下一个过滤器
            filterChain.doFilter(servletRequest,servletResponse);
        }else {//需要过滤器
            if (session!=null && session.getAttribute("user")!=null){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                String requestType = request.getHeader("X-Requested-With");
                //判断是否是ajax请求
                if (StringUtils.isNotBlank(requestType) && "XMLHttpRequest".equals(requestType)){
                    response.getWriter().write(this.NO_LOGIN);
                }else {
                    //重定向到登录页（需要在static文件夹下建立此html文件）
                    response.sendRedirect(request.getContextPath());
                }
            }
        }
    }
    
    /**
     * @Description: 是否需要过滤
     * @Param:
     * @Author: zhangpk
     */
    private boolean isNeedFilter(String uri) {
        boolean isNeed = true;
        for (String includeUrl : includeUrls) {
            if (includeUrl.equals(uri)){
                isNeed = false;
            }
        }
        return isNeed;
    }

    @Override
    public void destroy() {

    }
}
