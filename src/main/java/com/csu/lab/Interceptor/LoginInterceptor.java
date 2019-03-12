package com.csu.lab.Interceptor;

//import com.csu.bootone.Const.CustomConst;

import com.csu.lab.customConst.CustomConstant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        if (request.getSession().getAttribute(CustomConstant.IS_LOGIN) != null) {
            boolean is_login = (Boolean) request.getSession().getAttribute(CustomConstant.IS_LOGIN);
            if (!is_login) {
//            request.getRequestDispatcher()
                response.sendRedirect("/server/account/login");
            }
        } else {
//            带有/就是绝对的路径，没有带/就表示的相对的路径。
            response.sendRedirect("/server/account/login");
        }

        return true;
    }

    /**
     * 处理请求的时候会被调用，但是这个时候jsp页面还没有被渲染出来，其实就是可以对modelandView进行一些配置上的修改
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("PostHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
