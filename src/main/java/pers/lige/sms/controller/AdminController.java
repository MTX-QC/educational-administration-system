package pers.lige.sms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.lige.sms.entity.Admin;
import pers.lige.sms.service.AdminService;
import pers.lige.sms.util.UploadFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//系统管理
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    //存储预返回页面的对象信息
    Map<String, Object> result = new HashMap<>();

    /**
     * 跳转管理员信息的管理页面
     *
     * @return
     */
    @GetMapping("/goAdminListView")
    public String goAdminListView() {
        return "admin/adminList";
    }

    /**
     * 分页查询,根据管理员姓名获取指定用户/所有管理员信息列表
     *
     * @param page     当前页
     * @param rows     列表行数
     * @param username 管理员姓名
     * @return
     */
    @PostMapping("/getAdminList")
    @ResponseBody
    public Map<String, Object> getAdminList(Integer page, Integer rows, String username) {

        //获取查询的用户名
        Admin admin = new Admin();
        admin.setName(username);
        //设置每页记录数
        PageHelper.startPage(page, rows);
        //根据姓名获取指定或所有管理员列表信息
        //调用adminService完成
        List<Admin> list = adminService.selectList(admin);
        //封装查询结果
        PageInfo<Admin> pageInfo = new PageInfo<>(list);
        //获取总记录数
        long total = pageInfo.getTotal();
        //获取当前页数据列表
        List<Admin> adminList = pageInfo.getList();
        //存储对象数据
        result.put("total", total);
        result.put("rows", adminList);
        return result;
    }

    /**
     * 添加添加管理员信息
     *
     * @param admin
     * @return
     */
    @PostMapping("/addAdmin")
    @ResponseBody
    public Map<String, Object> addAdmin(Admin admin) {
        //添加的管理员用户名是否存在
        //调用adminService完成
        Admin user = adminService.findByName(admin.getName());
        if (user == null) {
            //添加操作
            try {
                if (adminService.insert(admin) > 0) {
                    result.put("success", true);
                } else {
                    result.put("success", false);
                    result.put("msg", "服务器发生异常,添加失败!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            result.put("success", false);
            result.put("msg", "该用户名已存在,请修改后重试!");
        }
        return result;
    }

    /**
     * 上传头像,原理:将头像上传到项目发布目录中,用过读取数据库中的头像路径来获取头像
     *
     * @param photo
     * @param request
     * @return
     */
    @PostMapping("/uploadPhoto")
    @ResponseBody
    public Map<String, Object> uploadPhoto(MultipartFile photo, HttpServletRequest request) {
        //存储头像到本地目录
        final String dirpath = request.getServletContext().getRealPath("/upload/admin_portrait/");
        //存储头像到项目发布目录
        final String portraitPath = request.getServletContext().getContextPath() + "/upload/admin_portrait/";
        //返回头像上传结果
        return UploadFile.getUploadResult(photo, dirpath, portraitPath);
    }

    /**
     * 根据id修改指定管理员信息
     *
     * @param admin
     * @return
     */
    @PostMapping("/editAdmin")
    @ResponseBody
    public Map<String, Object> editAdmin(Admin admin) {
        //需要排除用户只修改帐户名意外的信息
        Admin user = adminService.findByName(admin.getName());
        if (user != null) {
            if (!(admin.getId().equals(user.getId()))) {
                result.put("success", false);
                result.put("msg", "该用户名已经存在,请修改后重试!");
                return result;
            }
        }
//修改操作
        try {
            if (adminService.editAdmin(admin) > 0) {
                result.put("success", true);
            } else {
                result.put("success", false);
                result.put("msg", "修改失败,服务器发生异常!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除指定id的管理员信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteAdmin")
    @ResponseBody
    public Map<String, Object> deleteAdmin(@RequestParam(value = "ids[]", required = true) Integer[] ids) {
    if(adminService.deleteAdmin(ids)>0){
        result.put("success",true);
    }else {
        result.put("success",false);
    }
    return result;

    }

}
