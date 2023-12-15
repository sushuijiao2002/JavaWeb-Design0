package com.example.javaweb1.controller;

import com.example.javaweb1.controller.exception.*;
import com.example.javaweb1.service.exception.*;
import com.example.javaweb1.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;
/*处理异常类（控制层类的基类）*/
public class BaseController {
    public static final  int ok = 200;  //操作成功的状态码

    /*JsonResult<>数据的包装类型
     *handleException(Throwable e)请求处理方法，这个方法的返回值就是需要传递给前端的数据
     *@ExceptionHandler用于同一处理抛出的异常
     *自动将异常类对象传递给此方法的参数列表上
     *当前项目中产生了异常，被统一拦截到此方法中，这个方法此时就充当的是请求处理方法，方法的返回值直接给到前端*/
    @ExceptionHandler({Exception.class, FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e){

        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameOccupedException){
            result.setstatus(4000);
            result.setMessage("用户名已经被占用");
        }else if (e instanceof InsertException){
            result.setstatus(5000);
            result.setMessage("注册(插入数据)时产生未知异常");
        }else if (e instanceof UserNotFoundException){
            result.setstatus(5001);
            result.setMessage("用户数据不存在");
        } else if (e instanceof PasswordNotMatchException){
            result.setstatus(5002);
            result.setMessage("用户密码错误");
        }else if (e instanceof UpdateException){
            result.setstatus(5003);
            result.setMessage("更新数据时产生未知异常");
        }else if (e instanceof FileEmptyException) {
            result.setstatus(6000);
            result.setMessage("文件为空产生异常");
        } else if (e instanceof FileSizeException) {
            result.setstatus(6001);
            result.setMessage("文件太大产生异常");
        } else if (e instanceof FileTypeException) {
            result.setstatus(6002);
            result.setMessage("文件类型不对产生异常");
        } else if (e instanceof FileStatusException) {
            result.setstatus(6003);
        } else if (e instanceof FileUploadIOException) {
            result.setstatus(6004);
            result.setMessage("文件上传异常");
        }
        return result;

    }
    protected final int getIdFromSession(HttpSession session){

        return Integer.parseInt(session.getAttribute("id").toString());
    }

    /*获取当前登录用户的username
    * @param session session对象
    * @return 当前登录用户的用户名*/
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
