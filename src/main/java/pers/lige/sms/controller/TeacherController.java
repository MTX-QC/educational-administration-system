package pers.lige.sms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pers.lige.sms.entity.Teacher;
import pers.lige.sms.service.ClazzService;
import pers.lige.sms.service.TeacherService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private TeacherService teacherService;

    //存储预返回页面的数据对象
    private Map<String, Object> result = new HashMap<>();

    /**
     * 15 * @description: 跳转到教师信息管理页面
     * 16 * @param: modelAndView
     * 17
     * 18 * @return: org.springframework.web.servlet.ModelAndView
     * 19
     */
    @GetMapping("/goTeacherListView")
    public ModelAndView goTeacherListView(ModelAndView modelAndView) {
        //向页面发送一个存储着Clazz的List对象
        modelAndView.addObject("clazzList", clazzService.selectAll());
        modelAndView.setViewName("teacher/teacherList");
        return modelAndView;
    }

    @PostMapping("/getTeacherList")
    @ResponseBody
    public Map<String, Object> getTeacherList(Integer page, Integer rows, String teachername, String clazzname) {

        //存储查询的teachername,clazzname信息
        Teacher teacher = new Teacher(teachername, clazzname);
        //设置每页的记录数
        PageHelper.startPage(page, rows);
        //根据班级与教师名获取指定或全部教师信息列表
        List<Teacher> list = teacherService.selectList(teacher);
        //封装列表信息
        PageInfo<Teacher> pageInfo = new PageInfo<>(list);
        //获取总记录数
        long total = pageInfo.getTotal();
        //获取当前页数据列表
        List<Teacher> teacherList = pageInfo.getList();
        //存储数据对象
        result.put("total", total);
        result.put("rows", teacherList);

        return result;
    }
@PostMapping("/addTeacher")
@ResponseBody
public Map<String, Object> addStudent(Teacher teacher) {
         //判断工号是否已存在
         if (teacherService.findByTno(teacher) != null) {
             result.put("success", false);
             result.put("msg", "工号已存在! 请修改后重试!");
             return result;
             }
         if (teacherService.insert(teacher) > 0) {
             result.put("success", true);
             } else {
             result.put("success", false);
             result.put("msg", "添加失败! (ಥ_ಥ)服务器端发生异常!");
             }
         return result;
         }
@PostMapping("/editTeacher")
@ResponseBody
 public Map<String, Object> editTeacher(Teacher teacher) {
         if (teacherService.update(teacher) > 0) {
             result.put("success", true);
             } else {
             result.put("success", false);
             result.put("msg", "修改失败! (ಥ_ಥ)服务器端发生异常!");
             }
         return result;
         }
    @PostMapping("/deleteTeacher")
 @ResponseBody
 public Map<String, Object> deleteTeacher(@RequestParam(value = "ids[]", required = true) Integer[] ids) {

         if (teacherService.deleteById(ids) > 0) {
             result.put("success", true);
             } else {
             result.put("success", false);
             result.put("msg", "删除失败! (ಥ_ಥ)服务器端发生异常!");
             }
         return result;
         }
}