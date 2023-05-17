package pers.lige.sms.mapper;

import pers.lige.sms.entity.LoginForm;
import pers.lige.sms.entity.Student;

import java.util.List;

public interface StudentMapper {
    //学生登录
    Student login(LoginForm loginForm);

    List<Student> selectList(Student student);
    Student findBySno(Student student);
    int insert(Student student);

    int update(Student student);
    int deleteById(Integer[] ids);
    int updatePassword(Student student);
}
