package pers.lige.sms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.lige.sms.entity.Grade;
import pers.lige.sms.mapper.GradeMapper;
import pers.lige.sms.service.GradeService;

import java.util.List;

@Service
@Transactional
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeMapper gradeMapper;

    /**
     * 分页查询,根据年级名称查询指定或者所有年级列表信息
     *
     * @param grade
     * @return
     */
    @Override
    public List<Grade> selectList(Grade grade) {
        return gradeMapper.selectList(grade);
    }

    /**
     * 校验年级是否存在
     *
     * @param name
     * @return
     */
    @Override
    public Grade findByName(String name) {
        return gradeMapper.findByName(name);
    }

    /**
     * 年纪信息添加
     *
     * @param grade
     * @return
     */
    @Override
    public int insert(Grade grade) {
        return gradeMapper.insert(grade);
    }

    /**
     * 年级信息修改
     *
     * @param grade
     * @return
     */
    @Override
    public int update(Grade grade) {
        return gradeMapper.update(grade);
    }

    /**
     * 删除年级信息
     *
     * @param ids
     * @return
     */
    @Override
    public int deleteGradeById(Integer[] ids) {
        return gradeMapper.deleteGradeById(ids);
    }

    /**
     * 询所有年级信息(用于在班级管理页面中获取年级信息)
     *
     * @return
     */
    @Override
    public List<Grade> selectAll() {
        return gradeMapper.selectAll();
    }
}
