package com.huobangzhu.admin.controller;

import com.huobangzhu.core.domain.admin.Staff;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: lxl
 */
public class BaseController{

    public Staff getStaff(HttpServletRequest request){
        Staff staff = (Staff) request.getSession().getAttribute("staff");
        return staff;
    }
}
