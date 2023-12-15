package com.example.javaweb1.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllController {
    @GetMapping("/")
    public String homePage() {
        return "index.html"; // 这里返回的是HTML主页的文件名
    }
    @GetMapping("/MyArticle")
    public String articlePage() {
        return "Article1.html"; // 这里返回的是HTML主页的文件名
    }


}
