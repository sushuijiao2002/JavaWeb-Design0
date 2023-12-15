package com.example.javaweb1.mapper;
import com.example.javaweb1.entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //定义了一个方法insert，该方法接受一个Article对象作为参数。
    // 当这个方法被调用时，它会根据提供的Article对象的属性值，构建一个SQL插入语句，并将这些值插入到article表中。
    @Insert("insert into `article` (title,description,content,authorid,time,looknumber,goodnumber)"+
           "values(#{title},#{description},#{content},#{authorid},#{time},#{looknumber},#{goodnumber})")
    void insert (Article article);
    //定义了一个方法updateArticle，该方法接受一个Article对象作为参数。
    // 当这个方法被调用时，它会根据提供的Article对象的属性值，构建一个SQL更新语句，并更新数据库中对应ID的文章。
    @Update("update `article` set title = #{title},description = #{description},content = #{content},authorid = #{authorid},"+
            "time = #{time},looknumber = #{looknumber},goodnumber=#{goodnumber} where id=#{id}")
    void updateArticle(Article article);
    //定义了一个方法deleteArticle，该方法接受一个整型的ID作为参数。
    // 当这个方法被调用时，它会构建一个SQL删除语句，并从article表中删除对应ID的文章
    @Delete("delete from `article` where id = #{id}")
    void deleteArticle(Integer id);
    //定义了一个方法selectAll，该方法没有参数，并返回一个Article对象的列表。
    // 当这个方法被调用时，它会构建一个SQL查询语句，并从article表中查询所有文章，按ID降序排列
    @Select("select * from `article` order by id desc")
    List<Article> selectAll();
    //定义了一个方法selectById，该方法接受一个字符串的作者ID作为参数。
    // 当这个方法被调用时，它会构建一个SQL查询语句，并从article表中查询该作者的所有文章，按ID降序排列。
    @Select("select * from `article` where authorid = #{authorid} order by id desc")
    List<Article> selectById(String authorid);
    //定义了一个方法selectByTitle，该方法接受一个字符串的标题作为参数。
    // 当这个方法被调用时，它会构建一个SQL查询语句，并从article表中查询该标题的文章，按ID降序排列。
    @Select("select * from `article` where title = #{title} order by id desc")
    List<Article> selectByTitle(String title);
    //定义了一个方法selectByMore，该方法接受标题和描述作为参数。
    // 当这个方法被调用时，它会构建一个SQL查询语句，并从article表中查询具有指定标题和描述的文章，按ID降序排列。
    @Select("select * from `article` where title = #{title} and description =#{description} order by id desc")
    List<Article> selectByMore(String title, String description);

}
