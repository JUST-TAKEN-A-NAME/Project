package cn.hxp.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {
	protected HttpServletRequest request;  
    protected HttpServletResponse response;  
    protected HttpSession session;  
      
    
    // 调用controller方法之前，先把request，respont,sessiong等实例化，其他controller可以直接调用这些变量
    @ModelAttribute  
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;  
        this.response = response;  
        this.session = request.getSession();
    }
	 
    
}
