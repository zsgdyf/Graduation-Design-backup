package io.github.zsgdyf.LoveArticle.Action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import io.github.zsgdyf.Article.Article;
import io.github.zsgdyf.LoveArticle.LoveArticle;
import io.github.zsgdyf.LoveArticle.Mapper.LoveArticleMapper;

@RestController
public class LoveArticleAction {
	@Autowired
	private LoveArticleMapper loveArticleMapper;
	
	@RequestMapping(value="/getLoveArticle")
	public List<Article> getLoveArticle(Integer user_id) {
		return loveArticleMapper.select(user_id);
	}
	
	@RequestMapping(value="/loveArticle")
	public Object loveArticle(@RequestBody String loveArticle) {
		LoveArticle loveArticle2 = JSON.parseObject(loveArticle,LoveArticle.class);
		loveArticleMapper.insert(loveArticle2);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "收藏成功！");
		return jsonObject;
	}
}