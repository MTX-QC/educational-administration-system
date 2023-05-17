package pers.lige.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.lige.sms.entity.Admin;
import pers.lige.sms.entity.Student;
import pers.lige.sms.entity.Teacher;
import pers.lige.sms.service.AdminService;
import pers.lige.sms.service.StudentService;
import pers.lige.sms.service.TeacherService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AdminService adminService;
    //预返回对象信息
    private Map<String, Object> result = new HashMap<>();

    /**
     * 跳转管理信息个人管理页面
     *
     * @return
     */
    @GetMapping("/goSettingView")
    public String goSettingView() {
        return "common/settings";
    }

    /**
     * 修改密码
     *
     * @param oldPassword
     * @param newPassword
     * @param request
     * @return
     */
    @PostMapping("/editPassword")
    @ResponseBody
    public Map<String, Object> editPassword(String oldPassword, String newPassword, HttpServletRequest request) {
        //判断当前登陆用户的用户类型
        int userType = Integer.parseInt(request.getSession().getAttribute("userType").toString());
        //管理员身份
        if (userType == 1) {
            Admin admin = (Admin) request.getSession().getAttribute("userInfo");
            if (!admin.getPassword().equals(oldPassword)) {
                result.put("success", false);
                result.put("msg", "原密码错误");
                return result;
            }
            try {
                //修改密码
                admin.setPassword(newPassword);//覆盖旧密码存储待更新的新密码
                //调用adminService完成更新密码操作
                if (adminService.updatePassword(admin) > 0) {
                    result.put("success", true);
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.put("success", false);
                result.put("msg", "修改失败,服务器发生异常!");
            }
        }
        //学生身份
        if (userType == 2) {
            Student student = (Student) request.getSession().getAttribute("userInfo");
            if (!student.getPassword().equals(oldPassword)) {
                result.put("success", false);
                result.put("msg", "原密码错误");
                return result;
            }
            try {
                //修改密码
                student.setPassword(newPassword);//覆盖旧密码存储待更新的新密码
                //调用adminService完成更新密码操作
                if (studentService.updatePassword(student) > 0) {
                    result.put("success", true);
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.put("success", false);
                result.put("msg", "修改失败,服务器发生异常!");
            }
        }
        //老师身份
        //学生身份
        if (userType == 3) {
            Teacher teacher = (Teacher) request.getSession().getAttribute("userInfo");
            if (!teacher.getPassword().equals(oldPassword)) {
                result.put("success", false);
                result.put("msg", "原密码错误");
                return result;
            }
            try {
                //修改密码
                teacher.setPassword(newPassword);//覆盖旧密码存储待更新的新密码
                //调用adminService完成更新密码操作
                if (teacherService.updatePassword(teacher) > 0) {
                    result.put("success", true);
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.put("success", false);
                result.put("msg", "修改失败,服务器发生异常!");
            }
        }

        return result;

    }
}
