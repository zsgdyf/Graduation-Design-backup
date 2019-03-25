package io.github.zsgdyf.Article.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.github.zsgdyf.Article.Article;;

@Mapper
public interface ArticleMapper {
	@Insert("insert into article (title,content,author,creat_time) "
			+ "value (#{title},#{content},#{author},#{creat_time})")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(Article article);

	@Delete("delete from article where id=#{id}")
	Integer delete(Integer id);

	@Select("select * from article")
	List<Article> selectAll();

	@Update("update article set title=#{title},content=#{content}, where id=#{id}")
	Integer update(Integer id);
}