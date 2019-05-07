package io.github.zsgdyf.LoveArticle.Action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import io.github.zsgdyf.Article.Article;
import io.github.zsgdyf.Article.Mapper.ArticleMapper;
import io.github.zsgdyf.LoveArticle.LoveArticle;
import io.github.zsgdyf.LoveArticle.Mapper.LoveArticleMapper;

@RestController
public class LoveArticleAction {
	// 不推荐使用 filed 注入，重构使用构造器注入
	@Autowired
	private LoveArticleMapper loveArticleMapper;
	@Autowired
	private ArticleMapper articleMapper;

	@RequestMapping(value = "/getLoveArticle")
	public List<Article> getLoveArticle(Integer user_id) {
		return loveArticleMapper.select(user_id);
	}

	@RequestMapping(value = "/loveArticle")
	public Object loveArticle(@RequestBody String loveArticle) {
		LoveArticle loveArticle2 = JSON.parseObject(loveArticle,
				LoveArticle.class);
		loveArticleMapper.insert(loveArticle2);
		articleMapper.addLoveCount(loveArticle2.getArticle_id());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "收藏成功！");
		return jsonObject;
	}

	@RequestMapping(value = "/selectLoveExist")
	public Boolean selectLoveExist(Integer user_id, Integer article_id) {
		LoveArticle loveArticle = loveArticleMapper.selectLoveExist(user_id,
				article_id);
		if (loveArticle != null) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/selectUserLove")
	public List<LoveArticle> selectUserLove(Integer user_id) {
		return loveArticleMapper.selectUserLove(user_id);
	}
	
	@RequestMapping(value="/deleteLove")
	public Object deleteLove(Integer user_id, Integer article_id) {
		loveArticleMapper.delete(user_id, article_id);
		articleMapper.minusLoveCount(article_id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "取消收藏！");
		return jsonObject;
	}
	
//	@RequestMapping(value="/getLoveNumber")
//	public Integer getLoveNumber(Integer articleId) {
//		return loveArticleMapper.getLoveNumber(articleId);
//	}
}