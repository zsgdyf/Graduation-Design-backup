package io.github.zsgdyf.ArticleLabel.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.github.zsgdyf.ArticleLabel.ArticleLabel;
import io.github.zsgdyf.Label.Label;

@Mapper
public interface ArticleLabelMapper {
	@Select("select * from label where id in "
			+ "(select label_id from article_label where article_id=#{articleId})")
	List<Label> findLabelByArticleId(Integer articleId);
	
	@Insert("insert into article_label (article_id, label_id) "
			+ "values (#{articleId}, #{labelId})")
	void insert (ArticleLabel articleLabel);
}