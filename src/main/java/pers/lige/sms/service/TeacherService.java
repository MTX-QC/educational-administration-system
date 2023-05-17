package pers.lige.sms.service;

import pers.lige.sms.entity.Admin;
import pers.lige.sms.entity.LoginForm;
import pers.lige.sms.entity.Teacher;

import java.util.List;

public interface TeacherService {
    /**
     * 验证登录信息
     * @param loginForm
     * @return
     */
    Teacher login(LoginForm loginForm);

    /**
     * 教师身份修改密码
     * @param teacher
     * @return
     */
    int updatePassword(Teacher teacher);
    List<Teacher> selectList(Teacher teacher);

    int insert(Teacher teacher);

    int update(Teacher teacher);
    int deleteById(Integer[] ids);

    Teacher findByTno(Teacher teacher);
}
