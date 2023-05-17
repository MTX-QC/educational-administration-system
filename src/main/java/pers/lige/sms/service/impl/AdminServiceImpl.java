package pers.lige.sms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.lige.sms.entity.Admin;
import pers.lige.sms.entity.LoginForm;
import pers.lige.sms.mapper.AdminMapper;
import pers.lige.sms.service.AdminService;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 验证用户信息是否正确
     *
     * @param loginForm
     * @return
     */
    @Override
    public Admin login(LoginForm loginForm) {
        return adminMapper.login(loginForm);
    }

    /**
     * 根据管理员姓名查询指定或所有数据列表
     * @param admin
     * @return
     */
    @Override
    public List<Admin> selectList(Admin admin) {
        return adminMapper.selectList(admin);
    }

    /**
     * 根据用户名查询管理员信息
     * @param name
     * @return
     */
    @Override
    public Admin findByName(String name) {
        return adminMapper.findByName(name);
    }

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    @Override
    public int insert(Admin admin) {
        return adminMapper.insert(admin);
    }

    /**
     * 修改管理员信息
     * @param admin
     * @return
     */
    @Override
    public int editAdmin(Admin admin) {
        return adminMapper.editAdmin(admin);
    }

    /**
     * 删除指定id的管理员信息
     * @param ids
     * @return
     */
    @Override
    public int deleteAdmin(Integer[] ids) {
        return adminMapper.deleteAdmin(ids);
    }

    /**
     * 管理员修改密码
     * @param admin
     * @return
     */
    @Override
    public int updatePassword(Admin admin) {
        return adminMapper.updatePassword(admin);
    }
}
