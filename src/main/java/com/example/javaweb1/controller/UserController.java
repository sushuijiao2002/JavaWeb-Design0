package com.example.javaweb1.controller;

import com.example.javaweb1.controller.exception.*;
import com.example.javaweb1.entity.UserEntity;
import com.example.javaweb1.service.UserService;
import com.example.javaweb1.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    /*创建一个控制层对应类，依赖于业务层的接口*/
    private UserService userService;

    /*register(UserEntity user)接受前端数据
    * @ResponseBody表示此方法的响应结果以json格式进行数据的响应给到前端（不好）
    * @RestController=@ResponseBody+@Controller*/
    @RequestMapping("/register")
    public JsonResult<Void> register(UserEntity user) {
          userService.register(user);
          return new JsonResult<>(ok);
    }
    @RequestMapping("/login")
    public JsonResult<UserEntity> login(String username, String password, HttpSession session){
        UserEntity data = userService.login(username,password);
        session.setAttribute("id",data.getId());
        session.setAttribute("username",data.getUsername());
        System.out.println(getIdFromSession(session));
        System.out.println(getUsernameFromSession(session));
        return new JsonResult<UserEntity>(ok,data);
    }

    @RequestMapping("/changePassword")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
        int id = getIdFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(id,username,oldPassword,newPassword);
        return new  JsonResult<>(ok);
    }

    @RequestMapping("/getById")
    public JsonResult<UserEntity> getById(HttpSession session){
        UserEntity data = userService.getById(getIdFromSession(session));
        return  new JsonResult<>(ok,data);
    }
    @RequestMapping("/changeInfo")
    public JsonResult<Void> changeInfo(UserEntity user,HttpSession session){
        int id = getIdFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(id,username,user);
        return new JsonResult<>(ok);
    }



    /*修改头像*/
    public static final int Avatar_Max_Size = 10*1024*1024; //上传文件的最大值
    public static final List<String> Avatar_Type = new ArrayList<>();
    static {
        Avatar_Type.add("image/jpg");
        Avatar_Type.add("image/png");
        Avatar_Type.add("image/bmp");
        Avatar_Type.add("image/gif");
        Avatar_Type.add("image/x-png");
    }//限制上传文件的类型
    /**
     * MultipartFile接口是SpringMVC提供的一个接口，这个接口为我们包装了获取文件类型的数据（任何类型的file都可以接收）
     * SptingBoot整合了SpringMVC，我们只需要在处理请求的方法参数列表上声明一个参数类型为MultipartFile的参数，任何SpringBoot自动将文件数据赋值个这个参数
     * @RequestParam 表示请求中的参数，将请求中的参数注入请求处理方法的某个参数上，如果名称不一致可以使用RequestParam注解进行标记和映射
     * @param session
     * @param file
     * @return
     */
    @RequestMapping("changeAvatar")
    public JsonResult<String> changeAvatar(HttpSession session, @RequestParam("file") MultipartFile file){

        //判断文件是否为空
        if (file.isEmpty()){
            throw new FileEmptyException("上传的头像文件不允许为空");
        }
        if (file.getSize()>Avatar_Max_Size){
            throw new FileSizeException("文件超出限制"+Avatar_Max_Size);
        }
        //判断文件类型是否符合我们的规定
        String contantType = file.getContentType();
        //如果集合包含某个元素
        if (Avatar_Type.contains(contantType)){
            throw new FileTypeException(("文件类型不支持"));
        }
        //上传的文件.../upload/文件.jpg
        String parent = session.getServletContext().getRealPath("upload");
        System.out.println(parent);
        //File对象指向这个路径，File是否存在
        File dir = new File(parent);
        if (!dir.exists()){  //检测目录是否存在
            dir.mkdirs();  //创建当前的目录
        }
        //获取到这个文件名称，UUID工具来将生成一个新的字符串作为文件名
        //例如：avatar.jpg
        String originalFilename = file.getOriginalFilename();
        System.out.println("originalFilename="+originalFilename);
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String filename = UUID.randomUUID().toString().toUpperCase()+suffix;
        File dest = new File(dir,filename);
        //参数file中数据写入到这个空文件中
        try {
            file.transferTo(dest);  //将file文件中的数据写入到dest文件中
        } catch (FileStatusException e) {
            throw new FileStatusException("文件状态异常");
        }catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        }
        int id = getIdFromSession(session);
        String username = getUsernameFromSession(session);
        //返回头像的路径
        String avatar = "/upload/"+filename;
        userService.changeAvatar(id,avatar);  //将头像写入数据库中
        //返回用户头像的路径给前端页面，将来用于头像展示使用
        return new JsonResult<>(ok,avatar);
    }
    /*@RequestMapping("register")
    public JsonResult<Void> register(UserEntity user) {
        JsonResult<Void> result = new JsonResult<>();
        try {
            userService.register(user);
            result.setstatus(200);
            result.setMessage("用户注册成功");
        } catch (UsernameOccupedException e) {
            //throw new RuntimeException(e);
            result.setstatus(4000);
            result.setMessage("用户名已被占用");
        }catch (InsertException e) {
            //throw new RuntimeException(e);
            result.setstatus(5000);
            result.setMessage("注册时产生未知的异常");
        }
        return result;
    }
    @RequestMapping("login")
    public JsonResult<Void> login(String username,String password) {
        JsonResult<Void> resultt = new JsonResult<>();
        try {
            userService.login(username,password);
            resultt.setstatus(201);
            resultt.setMessage("用户登录成功");
        } catch (UserNotFoundException e) {
            resultt.setstatus(5001);
            resultt.setMessage("用户数据不存在");
        }catch (PasswordNotMatchException e) {
            resultt.setstatus(5002);
            resultt.setMessage("用户密码错误");
        }
        return resultt;
    }*/


}
