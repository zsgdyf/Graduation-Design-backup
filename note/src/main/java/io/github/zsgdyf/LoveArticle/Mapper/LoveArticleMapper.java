package io.github.zsgdyf.LoveArticle.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.github.zsgdyf.Article.Article;
import io.github.zsgdyf.LoveArticle.LoveArticle;

@Mapper
public interface LoveArticleMapper {
	@Insert("insert into love_article "
			+ "(user_id, article_id, love_time,love_date) "
			+ "values (#{user_id}, #{article_id},#{love_time},#{love_date})")
	void insert(LoveArticle loveArticle);

	@Select("select * from article where id in "
			+ "(select article_id from love_article where user_id = #{user_id})")
	List<Article> select(Integer user_id);

	@Select("select * from love_article where "
			+ "user_id=#{user_id} and article_id=#{article_id} limit 1")
	LoveArticle selectLoveExist(Integer user_id, Integer article_id);

	@Select("select * from love_article where user_id=#{user_id}")
	List<LoveArticle> selectUserLove(Integer user_id);

	@Delete("delete from love_article "
			+ "where user_id=#{user_id} and article_id=#{article_id}")
	void delete(Integer user_id, Integer article_id);
	
//	@Select("select count(1) from love_article where article_id=#{articleId}")
//	Integer getLoveNumber(Integer articleId);
}