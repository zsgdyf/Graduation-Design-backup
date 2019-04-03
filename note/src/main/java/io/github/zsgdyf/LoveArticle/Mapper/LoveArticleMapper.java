package io.github.zsgdyf.LoveArticle.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.github.zsgdyf.Article.Article;
import io.github.zsgdyf.LoveArticle.LoveArticle;

@Mapper
public interface LoveArticleMapper {
	@Insert("insert into love_article (user_id, article_id) values (#{user_id}, #{article_id})")
	void insert(LoveArticle loveArticle);
	
	@Select("select * from article where id in "
			+ "(select article_id from love_article where user_id = #{user_id})")
	List<Article> select(Integer user_id);
}