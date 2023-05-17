package pers.lige.sms.mapper;

import org.apache.ibatis.annotations.Param;
import pers.lige.sms.entity.Admin;
import pers.lige.sms.entity.LoginForm;

import java.util.List;

public interface AdminMapper {
    /**
     * 验证登录信息是否正确
     * @param loginForm
     * @return
     */
    Admin login(LoginForm loginForm);

    /**
     * 根据管理员姓名查询指定或所有数据列表
     * @param admin
     * @return
     */
    List<Admin> selectList(Admin admin);
    /**
     * 根据用户名查询管理员信息
     * @param name
     * @return
     */
    Admin findByName(String name);
    /**
     * 添加管理员
     * @param admin
     * @return
     */
    int insert(Admin admin);

    /**
     * 修改管理员信息
     * @param admin
     * @return
     */
    int editAdmin(Admin admin);

    /**
     * 删除指定id的管理员信息
     * @param ids
     * @return
     */
    int deleteAdmin(Integer[] ids);

    /**
     * 管理员修改密码
     * @param admin
     * @return
     */
    int updatePassword(Admin admin);
}
