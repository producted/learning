package com.haohuo.filter.convertfilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName ConvertFilter
 * @Description TODO
 * @Author Zhang Peike
 * @Date 2019/1/28 9:14
 **/
@Component
@WebFilter(filterName = "convertFileter",urlPatterns = "/**")
@Order(value = 0)
public class ConvertFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        if (servletRequest instanceof HttpServletRequest){
            HttpServletRequest request = (HttpServletRequest)servletRequest;

            requestWrapper = new MyRequestWrapper(request);
        }
        if (null == requestWrapper){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            filterChain.doFilter(requestWrapper,servletResponse);
        }


    }

    @Override
    public void destroy() {

    }
}

//为了防止流的获取只能获取一次，之后再获取就获取不到了，导致controller无法拿到参数，
// 需要通HttpServletRequestWrapper的getInputStream方法来重复获取流数据
class MyRequestWrapper extends HttpServletRequestWrapper{
    public static Logger logger = LoggerFactory.getLogger(MyRequestWrapper.class);
    byte[] body; // 报文

    public MyRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = StreamUtils.copyToByteArray(request.getInputStream());
        try{
            String bodystr = new String(body);
            logger.debug("RequestURI:{}",request.getRequestURI());
            logger.debug("QueryString:{}",request.getQueryString());
            logger.debug("RequestBody:{}",bodystr);
        }catch (Exception e){
        }
    }
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }
}
