package com.example.javaweb1.service;
import com.example.javaweb1.entity.Article;
import com.example.javaweb1.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArticleService {
    //用于自动装配。告诉Spring自动将合适的bean（在这个例子中是ArticleMapper）注入到这个字段。
    @Autowired
    ArticleMapper articleMapper;
    //这是一个公共方法，用于插入文章。它接受一个Article对象作为参数，并调用articleMapper的insert方法来执行插入操作。
    public  void insertArticle(Article article){
        articleMapper.insert(article);
    }
    //这是一个公共方法，用于更新文章。它接受一个Article对象作为参数，并调用articleMapper的updateArticle方法来执行更新操作。
    public void updateArticle(Article article) {
        articleMapper.updateArticle(article);
    }
    //这是一个公共方法，用于删除文章。它接受一个整型的ID作为参数，并调用articleMapper的deleteArticle方法来执行删除操作。
    public void deleteArticle(Integer id) {
        articleMapper.deleteArticle(id);
    }
    //这是一个公共方法，用于查询所有文章。它不接受任何参数，并调用articleMapper的selectAll方法来获取所有文章的列表
    public List<Article> selectAll() {
        return articleMapper.selectAll();
    }
    //这是一个公共方法，用于根据作者名查询文章。
    // 它接受一个字符串的作者名作为参数，并调用articleMapper的selectById方法来获取该作者的所有文章列表。
    public List<Article> selectById(String authorid) {
        return articleMapper.selectById(authorid);
    }
    //这是一个公共方法，用于根据标题查询文章。
    // 它接受一个字符串的标题作为参数，并调用articleMapper的selectByTitle方法来获取该标题的文章列表
    public List<Article> selectByTitle(String title) {
        return articleMapper.selectByTitle(title);
    }
    //这是一个公共方法，用于根据标题和描述查询文章。
    // 它接受标题和描述作为参数，并调用articleMapper的selectByMore方法来执行更复杂的查询操作。
    public List<Article> selectByMore(String title, String description) {
        return articleMapper.selectByMore(title,description);
    }


}
