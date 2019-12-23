package com.carson.demo.action;

import com.opensymphony.xwork2.ActionSupport;
import ognl.Ognl;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class UserAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public String execute() {
        System.out.println("测试有没有走！！！！！！！！！！");
        msg = "哈哈，我是struts2";
        setMsg(msg);
        return SUCCESS;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String login() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpServletResponse response = ServletActionContext.getResponse();
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if ("admin".equals(username) && "123456".equals(password)) {
                return SUCCESS;
            } else {
                return "login";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String index() {
        return SUCCESS;
    }


}