package pers.lige.sms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.lige.sms.entity.Grade;
import pers.lige.sms.service.GradeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;
    //预返回对象数据
    private Map<String, Object> result = new HashMap<>();

    /**
     * 跳转到年纪信息管理页面
     *
     * @return
     */
    @GetMapping("/goGradeListView")
    public String goGradeListView() {
        return "grade/gradeList";
    }

    /**
     * 分页查询,根据年级名称查询指定或者所有年级列表信息
     *
     * @param page
     * @param rows
     * @param gradename
     * @return
     */
    @PostMapping("/getGradeList")
    @ResponseBody
    public Map<String, Object> getGradeList(Integer page, Integer rows, String gradename) {
        Grade grade = new Grade();
        grade.setName(gradename);
        //设置每页记录数
        PageHelper.startPage(page, rows);
        //根据年级名称查询指定或者所有年级列表信息
        //调用gradeService完成
        List<Grade> list = gradeService.selectList(grade);
        //封装信息
        PageInfo<Grade> pageInfo = new PageInfo<>(list);
        //获取总记录数
        long total = pageInfo.getTotal();
        //获取当前数据列表
        List<Grade> gradeList = pageInfo.getList();
        //存储对象信息
        result.put("total", total);
        result.put("rows", gradeList);
        return result;
    }

    /**
     * 年级信息添加
     *
     * @param grade
     * @return
     */
    @PostMapping("/addGrade")
    @ResponseBody
    public Map<String, Object> addGrade(Grade grade) {

        //1.判断年级名称是否存在
        //调用gradeService完成
        Grade name = gradeService.findByName(grade.getName());
        if (name == null) {
            //2.添加操作
            if (gradeService.insert(grade) > 0) {
                result.put("success", true);

            } else {
                result.put("success", false);
                result.put("msg", "添加失败,服务器发生异常!");
            }

        } else {
            result.put("success", false);
            result.put("msg", "添加失败,您添加年级名称已存在,请修改后重试!");
        }
        return result;
    }

    /**
     * 年级信息管理修改
     *
     * @param grade
     * @return
     */
    @PostMapping("/editGrade")
    @ResponseBody
    public Map<String, Object> editGrade(Grade grade) {
        //1.排除用户修改用户名以外信息
        Grade g = gradeService.findByName(grade.getName());
        if (g != null) {
            if (!(g.getId().equals(grade.getId()))) {
                result.put("success", false);
                result.put("msg", "该年级用户名已经存在,请修改后重试!");
                return result;
            }
        }
        //添加操作
        if (gradeService.update(grade) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("msg", "修改失败,服务器异常!");
        }

        return result;
    }

    /**
     * 删除年级信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteGrade")
    @ResponseBody
    public Map<String,Object> deleteGrade(@RequestParam(value = "ids[]",required = true) Integer[] ids){

        if(gradeService.deleteGradeById(ids)>0){
            result.put("success",true);

        }else {
            result.put("success",false);
        }
        return result;
    }

}
