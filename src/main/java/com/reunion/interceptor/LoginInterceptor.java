package com.reunion.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

// WebMvcContextConfiguration 에 인터셉터를 등록해야 사용할 수 있다.
public class LoginInterceptor extends HandlerInterceptorAdapter {
    // DispatcherServlet에서 Controller에게 요청이 전달되기 전에 실행된다.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String loginId = (String)session.getAttribute("loginId");

        // 로그인을 해야만 접근할 수 있는 path목록
        List<String> securePaths = Arrays.asList(
                                "/reunion/write",
                                "/reunion/update",
                                "/member/showInfo");
        String servletPath = request.getServletPath();

        if(securePaths.contains(servletPath)) {
            if(loginId == null || "".equals(loginId)){
                response.sendRedirect("/member/login?referer=" + servletPath);
            }
        }
        return true; // false 로 넘기면 이미 처리한것으로 생각해서 Controller로 넘어가지 않는다.
    }
}

// http://localhost:8080/boards/writeform (로그인 안한 상태)
// LoginInterceptor가 아래와 같은 URL로 리다이렉트
// http://localhost:8080/users/loginform?referer=/boards/writeform
// 로그인 폼에는 hidden태그로 어디로 리다이렉트할지 그정보를 설정한다.
// 로그인 버튼을 누르면, id, pasword, referer 정보가 login처리 페이지로 전달된다.
// login처리페이지에서는 로그인을 처리한 후 /boards로 이동하든지, referer가 있다면 referer로 이동한다.