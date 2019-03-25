package io.github.zsgdyf.Article.Action;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import io.github.zsgdyf.Article.*;
import io.github.zsgdyf.Article.Mapper.ArticleMapper;

@RestController
public class ArticleAction{
	@Autowired
	private ArticleMapper articleMapper;
	
	@RequestMapping(value="/newArticle")
	public Object insert(Article article) {
		articleMapper.insert(article);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "提交成功！");
		return jsonObject;
	}
}