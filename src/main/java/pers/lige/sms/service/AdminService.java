package pers.lige.sms.service;

import pers.lige.sms.entity.Admin;
import pers.lige.sms.entity.LoginForm;

import java.util.List;

public interface AdminService {
    //验证登录信息是否正确
    Admin login(LoginForm loginForm);

    //根据管理员姓名查询指定或所有数据列表
    List<Admin> selectList(Admin admin);

    //根据用户名查询管理员信息
    Admin findByName(String name);

    //添加管理员信息
    int insert(Admin admin);

    //修改管理员信息
    int editAdmin(Admin admin);

    //删除指定id的管理员信息
    int deleteAdmin(Integer[] ids);

    //管理员修改密码
    int updatePassword(Admin admin);
}
