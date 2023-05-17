package pers.lige.sms.mapper;

import pers.lige.sms.entity.Clazz;

import java.util.List;

public interface ClazzMapper {
    //分页查询班级信息,根据班级与年级名查询指定或者所有班级信息列表
    List<Clazz> selectList(Clazz clazz);

    //添加前查重名称
    Clazz findByName(String name);

    //班级信息添加
    int insert(Clazz clazz);

    //修改班级信息
    int update(Clazz clazz);

    //删除班级信息列表
    int deleteById(Integer[] ids);

    //查询所有班级信息
    List<Clazz> selectAll();
}
