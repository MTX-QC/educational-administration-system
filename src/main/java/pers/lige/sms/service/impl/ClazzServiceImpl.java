package pers.lige.sms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.lige.sms.entity.Clazz;
import pers.lige.sms.entity.Grade;
import pers.lige.sms.mapper.ClazzMapper;
import pers.lige.sms.service.ClazzService;

import java.util.List;

@Service
@Transactional
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    /**
     * 分页查询班级信息,根据班级与年级名查询指定或者所有班级信息列表
     * @param clazz
     * @return
     */
    @Override
    public List<Clazz> selectList(Clazz clazz) {
        return clazzMapper.selectList(clazz);
    }

    /**
     * 添加前查重名称
     * @param name
     * @return
     */
    @Override
    public Clazz findByName(String name) {
        return clazzMapper.findByName(name);
    }

    /**
     *班级信息添加
     * @param clazz
     * @return
     */
    @Override
    public int insert(Clazz clazz) {
        return clazzMapper.insert(clazz);
    }

    /**
     * 修改班级信息
     * @param clazz
     * @return
     */
    @Override
    public int update(Clazz clazz) {
        return clazzMapper.update(clazz);
    }

    /**
     * 删除班级信息列表
     * @param ids
     * @return
     */
    @Override
    public int deleteById(Integer[] ids) {
        return clazzMapper.deleteById(ids);
    }

    @Override
    public List<Clazz> selectAll() {
        return clazzMapper.selectAll();
    }
}
