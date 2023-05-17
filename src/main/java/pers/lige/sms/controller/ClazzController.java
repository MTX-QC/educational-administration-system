package pers.lige.sms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pers.lige.sms.entity.Clazz;
import pers.lige.sms.entity.Grade;
import pers.lige.sms.service.ClazzService;
import pers.lige.sms.service.GradeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/clazz")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    @Autowired
    private GradeService gradeService;
    //预返回对象数据
    Map<String, Object> resultMap = new HashMap<>();

    /**
     * 跳转转到班级信息管理页面
     *
     * @param modelAndView
     * @return
     */
    @GetMapping("/goClazzListView")
    public ModelAndView goClazzListView(ModelAndView modelAndView) {
        //向页面发送一个存储Grade的List对象
        modelAndView.addObject("gradeList", gradeService.selectAll());
        //设置返回的页面
        modelAndView.setViewName("clazz/clazzList");
        //返回modelAndView
        return modelAndView;
    }

    /**
     * 分页查询班级信息,根据班级与年级名查询指定或者所有班级信息列表
     *
     * @param page
     * @param rows
     * @param clazzname
     * @param gradename
     * @return
     */
    @PostMapping("/getClazzList")
    @ResponseBody
    public Map<String, Object> getClazzList(Integer page, Integer rows, String clazzname, String gradename) {
        //存储查询clazzname,gradename信息
        Clazz clazz = new Clazz(clazzname, gradename);
        //设置每页记录数
        PageHelper.startPage(page, rows);
        //分页查询班级信息,根据班级与年级名查询指定或者所有班级信息列表
        List<Clazz> list = clazzService.selectList(clazz);
        //封装列表信息
        PageInfo<Clazz> pageInfo = new PageInfo<>(list);
        //获取总记录数
        long total = pageInfo.getTotal();
        //获取当前页数据列表
        List<Clazz> clazzList = pageInfo.getList();
        //存储对象数据
        resultMap.put("total", total);
        resultMap.put("rows", clazzList);
        return resultMap;


    }

    /**
     * 班级信息管理添加
     *
     * @param clazz
     * @return
     */
    @PostMapping("/addClazz")
    @ResponseBody
    public Map<String, Object> addClazz(Clazz clazz) {
        //判断班级名称是否存在
        Clazz name = clazzService.findByName(clazz.getName());
        if (name == null) {
            //添加操作
            if (clazzService.insert(clazz) > 0) {
                resultMap.put("success", true);
            } else {
                //失败
                resultMap.put("success", false);
                resultMap.put("msg", "添加失败,服务器异常!");
            }
        } else {
            //添加失败
            resultMap.put("success", false);
            resultMap.put("msg", "添加失败,请修改班级名再重试!");
        }
        return resultMap;

    }

    /**
     * 修改班级信息管理
     *
     * @param clazz
     * @return
     */
    @PostMapping("/editClazz")
    @ResponseBody
    public Map<String, Object> editClazz(Clazz clazz) {
        //1.排除用户名只修改班级名以外信息
        Clazz c = clazzService.findByName(clazz.getName());
        if (c != null) {
            if (!(clazz.getId().equals(c.getId()))) {
                resultMap.put("success", false);
                resultMap.put("msg", "该班级名已存在,请修改班级名再重试!");
                return resultMap;
            }

        }
        try {
            //修改操作
            if (clazzService.update(clazz) > 0) {
                resultMap.put("success", true);
            } else {
                resultMap.put("success", false);
                resultMap.put("msg", "修改失败,服务器异常!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;

    }

    /**
     * 删除班级信息管理列表
     * @param ids
     * @return
     */
    @PostMapping("/deleteClazz")
    @ResponseBody
    public Map<String, Object> deleteClazz(@RequestParam(value = "ids[]",required = true) Integer[] ids){
        //删除操作
        if(clazzService.deleteById(ids)>0){
            resultMap.put("success",true);
        }else {
            resultMap.put("success", false);
            resultMap.put("msg", "删除失败,服务器异常!");
        }
        return resultMap;
    }
}
