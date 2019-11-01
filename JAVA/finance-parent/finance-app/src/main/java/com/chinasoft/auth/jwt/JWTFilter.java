package com.chinasoft.auth.jwt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.chinasoft.util.code.StatusCode;
import com.chinasoft.vo.output.ResultObject;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义鉴权Filter
 * <p>
 * 代码的执行流程 preHandle->isAccessAllowed->onAccessDenied
 */
public class JWTFilter extends AccessControlFilter {

    private Logger logger = LoggerFactory.getLogger(JWTFilter.class);

    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     */
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        return authorization != null;
    }

    /**
     * 请求头中有token,执行自动登录
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("Authorization");

        JWTToken token = new JWTToken(authorization);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(token);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 访问控制
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return 如果没有登录就返回false, 进入onAccessDenied()方法进行处理; 如果已经登录就直接放行,进入指定的Controller方法
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return null != getSubject(request, response)
                && getSubject(request, response).isAuthenticated();
    }

    /**
     * isAccessAllowed() 方法返回 false 时，通过该方法处理请求
     *
     * @param request
     * @param response
     * @return false 表示已经处理请求，不继续往下走； true 继续往下走
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
                return true;
            } catch (AuthenticationException e) {
                logger.error(e.getMessage(),e);
                // 登录失败，token无效
                responseInvalidToken(response,e);
                return false;
            }
        }
        responseInvalidToken(response,null);
        return false;
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        /*httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }*/
        return super.preHandle(request, response);
    }

    private void responseInvalidToken(ServletResponse response,Exception e) {
        try {
            response.setContentType("application/json; charset=utf-8");
            String msg = "token无效，请重新登录";
            if(e!=null){
                msg = e.getMessage();
            }
            ResultObject resultObject = ResultObject.ResultObjectBuilder.aResultObject()
                    .withResultMsg(msg)
                    .withResultCode(StatusCode.STATUS_USER_TOKEN_INVALID)
                    .build();
            response.getWriter().println(JSON.toJSONString(resultObject, SerializerFeature.WriteMapNullValue));
            response.getWriter().close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}


