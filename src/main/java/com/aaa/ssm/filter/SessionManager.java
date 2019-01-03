package com.aaa.ssm.filter;



import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * className:SessionManager
 * discription:
 * author:fhm
 * createTime:2018-12-31 16:24
 */
public class SessionManager  implements Filter{

    private static final Logger log=Logger.getLogger(SessionManager.class.getName());
    private static final String SMCS_LOG="SUIHUASMCSLOG";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        //cookies
        Cookie[] cookies=request.getCookies();
        Cookie ACookie=null;
        boolean HaveSmcsSession=false;//不包含

        String uuid= UUID.randomUUID().toString();
        String sessionID=uuid.replaceAll("-","");
        System.out.println("添加smcs_sessionID==="+sessionID);

        if (cookies!=null){
            for (int i=0;i<cookies.length;i++){
                ACookie = cookies[i];
                String cookiename=ACookie.getName();
                String cookievalue=ACookie.getValue();
                if(cookiename.equals(SessionManager.SMCS_LOG)){
                    sessionID=cookievalue;
                    HaveSmcsSession=true;
                    break;
                }
            }
            //不包含
            if (!HaveSmcsSession){
                Cookie cookie_smcs=new Cookie(SessionManager.SMCS_LOG,sessionID);
                cookie_smcs.setPath("/"); //这个要设置
                // 不设置的话，则cookies不写入硬盘,而是写在内存,只在当前页面有用,以秒为单位
                response.addCookie(cookie_smcs);
            }
        }else if (cookies==null){
            Cookie cookie_smcs=new Cookie(SessionManager.SMCS_LOG,sessionID);
            cookie_smcs.setPath("/"); //这个要设置
            // 不设置的话，则cookies不写入硬盘,而是写在内存,只在当前页面有用,以秒为单位
            response.addCookie(cookie_smcs);
        }
        ServletContext ctx=request.getSession().getServletContext();
        SmcsSession session= new SmcsSession();
        if (ctx.getAttribute(sessionID)==null){
            ctx.setAttribute(sessionID,session);
        }
        log.info("my session manager....");
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }

    public static SmcsSession getSession(HttpServletRequest request){
        ServletContext ctx=request.getSession().getServletContext();
        Cookie[] cookies=request.getCookies();
        SmcsSession session=new SmcsSession();
        String sessionID=null;
        if (cookies!=null){
            for (int i=0;i<cookies.length;i++){
                Cookie ACookie=cookies[i];
                String cookiename=ACookie.getName();
                String cookievalue=ACookie.getValue();
                if (cookiename.equals(SessionManager.SMCS_LOG)){
                    sessionID=cookievalue;
                    break;
                }
            }
        }
        if (sessionID==null)
            return session;
        return (SmcsSession) ctx.getAttribute(sessionID);
    }

}
