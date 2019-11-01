package com.chinasoft.config.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

public class LogFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //记录请求日志
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;
        String clientIP = httpReq.getRemoteHost();
        String uri = httpReq.getRequestURI();
        String method = httpReq.getMethod();
        String contentType = httpReq.getContentType();
        Map<String, String[]> paramMap = httpReq.getParameterMap();
        Enumeration<String> headerNames = httpReq.getHeaderNames();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("请求开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
        long startTime = System.currentTimeMillis();                                   //运行前的时间
        stringBuilder.append(method).append(" ").append(uri).append("\n");
        stringBuilder.append("clientIp: ").append(clientIP).append("\n");


        stringBuilder.append("header: \n");
        for (String header : Collections.list(headerNames)) {
            stringBuilder.append("        ")
                    .append(header)
                    .append(": ")
                    .append(httpReq.getHeader(header))
                    .append("\n");
        }
        if (!paramMap.isEmpty()) {
            stringBuilder.append("parameter: \n");
            for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
                StringBuilder values = new StringBuilder();
                for (String value : entry.getValue()) {
                    values.append(value).append(",");
                }
                stringBuilder.append("        ").append(entry.getKey()).append(": ").append(values.toString()).append("\n");
            }
        }
        if (contentType != null && contentType.contains("multipart/form-data")) {//文件上传请求,避免出现文件内容乱码
            chain.doFilter(httpReq, response);
        }else if(uri.indexOf("exportMemberExcel")!=-1){//
            chain.doFilter(httpReq, response);
        }else if(uri.indexOf("exportAllCom")!=-1){//
            chain.doFilter(httpReq, response);
        }  else {
            MyRequestWrapper myRequestWrapper = new MyRequestWrapper(httpReq);
            MyResponseWrapper myResponseWrapper = new MyResponseWrapper(httpRes);
            String body = myRequestWrapper.getBody();
            if (!body.equals("")) {
                stringBuilder.append("request body: ").append(body).append("\n");
            }
            logger.info(stringBuilder.toString());
            chain.doFilter(myRequestWrapper, myResponseWrapper);
            //记录响应日志
            String result = new String(myResponseWrapper.getResponseData());  //获取输出内容
            ServletOutputStream out = response.getOutputStream();
            response.setContentLength(result.getBytes().length);
            out.write(myResponseWrapper.getResponseData(), 0, myResponseWrapper.getResponseData().length);         //重新输出内容
            out.flush();
            out.close();
            logger.info("请求结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\nresponse body:\n" + result + "\n");
        }
        long endTime = System.currentTimeMillis();
        //消耗的总时间
        logger.info(request.getRemoteAddr() + " 访问了 " + uri + ", 总用时 " + (endTime - startTime) + " 毫秒。");


    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}