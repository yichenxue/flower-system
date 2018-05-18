package com.flower.config;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by yumaoying on 2018/4/1.
 * 用户表单验证及登陆问题解决
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    /***
     * 验证短信验证码的实现
     * 验证输入验证码，shiro使用FormAuthenticationFilter进行表单认证
     * 验证校验的功能应该加在FormAuthenticationFilter中，在认证之前进行验证码校验。
     * @param request 请求
     * @param response 响应
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        //获取短信验证码
        String validateCode = (String) session.getAttribute("validateCode");
        //获取输入的短信验证码
        String randomCode = httpServletRequest.getParameter("randomCode");
        if (randomCode != null && validateCode != null && !randomCode.equalsIgnoreCase(validateCode)) {
            //如果校验失败，将验证码错误失败信息，通过shiroLoginFailure设置到request中
            httpServletRequest.setAttribute("shiroLoginFailure", "kaptchaValidateFailed");
            //拒绝访问,不在校验账号和密码
            return true;
        }
        return super.onAccessDenied(request, response);
    }


    /***
     * 解决登陆成功后跳转到登陆之前请求的路径问题
     *shiro处理重定向清理session中保存的请求信息，跳转到指定路径
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.getAndClearSavedRequest(request);//清除原先的地址
        WebUtils.redirectToSavedRequest(request, response, "/index");
        return false;
    }

}
