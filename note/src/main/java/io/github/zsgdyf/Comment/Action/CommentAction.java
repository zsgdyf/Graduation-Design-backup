package io.github.zsgdyf.Comment.Action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import io.github.zsgdyf.Comment.Comment;
import io.github.zsgdyf.Comment.Mapper.CommentMapper;

@RestController
public class CommentAction{
	@Autowired
	private CommentMapper commentMapper;
	
	@RequestMapping(value="/newComment")
	public Object newComment(@RequestBody String comment) {
		Comment comment2 = JSON.parseObject(comment, Comment.class);
		commentMapper.insert(comment2);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "发布成功！");
		return jsonObject;
	}
}