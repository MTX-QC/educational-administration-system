package pers.lige.sms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.lige.sms.entity.Admin;
import pers.lige.sms.entity.LoginForm;
import pers.lige.sms.entity.Student;
import pers.lige.sms.entity.Teacher;
import pers.lige.sms.service.AdminService;
import pers.lige.sms.service.StudentService;
import pers.lige.sms.service.TeacherService;
import pers.lige.sms.util.CreateVerifiCodeImage;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/system")
public class SystemController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    //存储返回页面的对象数据
    private Map<String, Object> result = new HashMap<>();

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @GetMapping("/goLogin")
    public String goLogin() {
        return "system/login";
    }

    /**
     * 获取并显示验证码图片
     *
     * @param request
     * @param response
     */
    @GetMapping("/getVerifiCodeImage")
    public void getVerifiCodeImage(HttpServletRequest request, HttpServletResponse response) {
        //1.获取到验证码图片
        BufferedImage verifiCodeImage = CreateVerifiCodeImage.getVerifiCodeImage();
        //2.验证码
        String verifyCode = String.valueOf(CreateVerifiCodeImage.getVerifiCode());
        //3.将验证码图片输出到登录页面
        try {
            ImageIO.write(verifiCodeImage, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //存储验证码session
        request.getSession().setAttribute("verifiCode", verifyCode);

    }

    /**
     * 用户登录
     *
     * @param loginForm
     * @param request
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public Map<String,Object> login(LoginForm loginForm, HttpServletRequest request) {
        //1.校验验证码信息  取出生成验证码
        String code = (String) request.getSession().getAttribute("verifiCode");
        if ("".equals(code)) {
            result.put("success", false);
            result.put("msg", "长时间不操作,会话失效,请重新刷新页面后重试!");
            return result;
        } else if (!loginForm.getVerifiCode().equalsIgnoreCase(code)) {
            result.put("success", false);
            result.put("msg", "验证码错误");
            return result;
        }
        //登录之后清除之前验证码
        request.getSession().removeAttribute("verifiCode");
        //校验用户的登录信息
        switch (loginForm.getUserType()) {
            //1.管理员登录
            case "1":
                try {
                    //调用AdminService完成查询操作 验证账户和密码是否存在
                    Admin admin = adminService.login(loginForm);
                    if (admin != null) {
                        //存储到session
                        HttpSession session = request.getSession();
                        session.setAttribute("userInfo", admin);
                        session.setAttribute("userType", loginForm.getUserType());
                        result.put("success", true);
                        return result;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("success", false);
                    result.put("msg", "登录失败服务器发生异常!");
                }
                break;
            //学生登陆
            case "2":

                try {
                    //调用studentService完成查询,校验账户和密码是否存在

                    Student student = studentService.login(loginForm);
                    if (student != null) {
                        //存储到session
                        HttpSession session = request.getSession();
                        session.setAttribute("userInfo", student);
                        session.setAttribute("userType", loginForm.getUserType());
                        result.put("success", true);
                        return result;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("success", false);
                    result.put("msg", "登录失败服务器发生异常!");
                }
                break;
            //教师登录
            case "3":
                try {

                    //调用studentService完成查询,校验账户和密码是否存在
                    Teacher teacher = teacherService.login(loginForm);
                    if (teacher != null) {
                        //存储到session
                        HttpSession session = request.getSession();
                        session.setAttribute("userInfo", teacher);
                        session.setAttribute("userType", loginForm.getUserType());
                        result.put("success", true);
                        return result;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("success", false);
                    result.put("msg", "登录失败服务器发生异常!");
                }

                break;
        }
        //登录失败
        result.put("success", false);
        result.put("msg", "用户名或者密码错误!");
        return result;
    }

    /**
     * 跳转到系统主页面
     * @return
     */
    @GetMapping("/goSystemMainView")
    public String goSystemMainView() {
        return "system/main";
    }

    /**
     * 用户退出
     * @param request
     * @param response
     */
    @GetMapping("/loginOut")
    public void loginOut(HttpServletRequest request,HttpServletResponse response){
        //1.清除session
        request.getSession().removeAttribute("userInfo");
        request.getSession().removeAttribute("userType");
        //注销后重定向到登录页面
        try {
            response.sendRedirect("../index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
