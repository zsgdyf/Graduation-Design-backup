package io.github.zsgdyf.Article.Action;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.zsgdyf.Article.*;
import io.github.zsgdyf.Article.Mapper.ArticleMapper;

@RestController
public class ArticleAction {
	@Autowired
	private ArticleMapper articleMapper;

	@RequestMapping(value = "/newArticle")
	public Object insert(@RequestBody String article) {
		Article article2 = JSON.parseObject(article, Article.class);
		articleMapper.insert(article2);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("state", article2.getState());
		return jsonObject;
	}

	@RequestMapping(value = "/popularArticles")
	public List<Article> selectPopular() {
		return articleMapper.selectPopular();
	}

	@RequestMapping(value = "/myNotes")
	public List<Article> select(String author) {
		return articleMapper.selectAll(author);
	}

	@RequestMapping(value = "/articles")
	public Article selectOne(Integer id) {
		return articleMapper.selectOne(id);
	}

	@RequestMapping(value = "/deleteArticle")
	public Object delete(Integer id) {
		articleMapper.delete(id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "已删除！");
		return jsonObject;
	}

	@RequestMapping(value = "/updateArticle")
	public Object updateAticle(@RequestBody String article) {
		Article article2 = JSON.parseObject(article, Article.class);
		articleMapper.update(article2.getTitle(), article2.getContent(),
				article2.getContent_md(), article2.getId());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("state", article2.getState());
		return jsonObject;
	}
}