package pers.lige.sms.mapper;

import pers.lige.sms.entity.Grade;

import java.util.List;

public interface GradeMapper {
    //分页查询,根据年级名称查询指定或者所有年级列表信息
    List<Grade> selectList(Grade grade);

    //校验年级名称是否存在
    Grade findByName(String name);

    //年级信息添加
    int insert(Grade grade);

    //年级信息修改
    int update(Grade grade);

    //删除年纪信息
    int deleteGradeById(Integer[] ids);

    //查询所有年级信息(用于在班级管理页面中获取年级信息)
    List<Grade> selectAll();
}
