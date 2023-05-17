package pers.lige.sms.service;

import pers.lige.sms.entity.Clazz;
import pers.lige.sms.entity.Grade;

import java.util.List;

public interface ClazzService {
    //分页查询班级信息,根据班级与年级名查询指定或者所有班级信息列表
    List<Clazz> selectList(Clazz clazz);

    //根据班级名称查重
    Clazz findByName(String name);

    //添加班级信息
    int insert(Clazz clazz);

    //修改班级信息
    int update(Clazz clazz);

    //删除班级信息列表
    int deleteById(Integer[] ids);

    List<Clazz> selectAll();
}
