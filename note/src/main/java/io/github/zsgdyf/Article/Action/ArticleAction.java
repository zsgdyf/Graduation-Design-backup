package io.github.zsgdyf.Article.Action;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		Article article2 = JSON.parseObject(article,Article.class);
		articleMapper.insert(article2);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "发布成功！");
		return jsonObject;
	}
	
	@RequestMapping(value = "/myNotes")
	public List<Article> select(String author){
		return articleMapper.selectAll(author);
	}
}