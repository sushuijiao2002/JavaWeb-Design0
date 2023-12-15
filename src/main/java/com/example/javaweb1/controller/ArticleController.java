package com.example.javaweb1.controller;

import cn.hutool.core.date.DateUtil;
import com.example.javaweb1.entity.Article;
import com.example.javaweb1.service.ArticleService;
import com.example.javaweb1.util.JsonArticleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.javaweb1.controller.BaseController.ok;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
   private ArticleService articleService;
    /**
     * 新增文章
     */
    @RequestMapping("/add")
    public JsonArticleResult<Void> add(Article article) {
        article.setTime(DateUtil.now());//设置文章时间为当前时间
        articleService.insertArticle(article);//调用articleService的insertArticle方法来保存文章
        return new JsonArticleResult<>(ok);
    }

    /**
     * 修改文章
     */
    @PutMapping ("/update")
    public JsonArticleResult<Void> update(@RequestBody Article article) {
        articleService.updateArticle(article);//直接调用articleService的updateArticle方法来更新文章。
        return new JsonArticleResult<>(ok);
    }

    /**
     * 删除信息
     */
    @DeleteMapping("/delete/{id}")
    public JsonArticleResult<Void> delete(@PathVariable Integer id) {
        articleService.deleteArticle(id);
        return new JsonArticleResult<>(ok);
    }

    /**
     * 查询全部信息
     * 使用PathVariable来获取要删除的文章的ID，并调用articleService的deleteArticle方法来删除文章。
     */
    @GetMapping("/selectAll")
    public JsonArticleResult<List<Article>> selectAll() {
        List<Article> articleList = articleService.selectAll();
        return new JsonArticleResult<>(ok,articleList);
    }
    /*根据作者名查询
    /*根据作者名查询文章的请求。使用PathVariable来获取作者ID，并调用articleService的selectById方法来获取文章列表
     */
    @RequestMapping("/selectById/{authorid}")
    public JsonArticleResult<List<Article>> selectById(@PathVariable String authorid) {
        List<Article> articleList = articleService.selectById(authorid);
        return new JsonArticleResult<>(ok,articleList);
    }


    /*
     *根据标题查询信息
     * 根据条件查询时，如果不确定返回的对象有几个，就统一返回List对象集合
     * 使用PathVariable来获取标题，并调用articleService的selectByTitle方法来获取文章列表。
     */
    @RequestMapping("/selectByTitle/{title}")
    public JsonArticleResult<List<Article>> selectByTitle(@PathVariable String title) {
        List<Article> articleList = articleService.selectByTitle(title);
        return new JsonArticleResult<>(ok,articleList);
    }
    /*
    /*多条件查询
     */
    @GetMapping("/selectByMore")//该方法处理的是HTTP GET请求，请求的URL路径是/selectByMore。
    //这个方法期望在请求的查询参数中接收名为title和description的值。@RequestParam表示该参数应该从请求的查询参数中提取。
    public JsonArticleResult<List<Article>> selectByMore(@RequestParam String title,@RequestParam String description){
        //这行代码调用articleService的selectByMore方法，并传递了从请求中提取的title和description参数。
        List<Article> articleList =articleService.selectByMore(title,description);
       // 这行代码创建了一个新的JsonArticleResult对象，并传递了两个参数：一个表示操作成功的状态，另一个是文章列表。
       // 然后这个新的JsonArticleResult对象被返回，作为这个HTTP GET请求的响应体。
        return new JsonArticleResult<>(ok,articleList);
    }


}
