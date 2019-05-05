package io.github.zsgdyf.User.Action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import io.github.zsgdyf.User.User;
import io.github.zsgdyf.User.Mapper.UserMapper;

@RestController
public class UserAction {
	// 不推荐使用 field 注入，应使用构造器注入
	@Autowired
	private UserMapper UserMapper;

	@RequestMapping(value = "/insert")
	public Object insert(User User) {
		if (UserMapper.selectByName(User.getName()) != null) {
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("message", "用户名已被使用！");
			return jsonobject;
		} else {
			UserMapper.insert(User);
			return UserMapper.selectByName(User.getName());
		}
	}

	@RequestMapping(value = "/selectById")
	public User selectById(@RequestParam Integer id) {
		return UserMapper.selectById(id);
	}

	private User selectByName(String name) {
		return UserMapper.selectByName(name);
	}

	@RequestMapping(value = "/selectAll")
	public List<User> selectAll() {
		return UserMapper.selectAll();
	}

	@RequestMapping("/delete")
	public void delete(Integer id) {
		UserMapper.delete(id);
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void update(@RequestParam User User) {
		UserMapper.update(User);
	}

	private boolean comparePassword(User user, User userInDatabase) {
		return user.getPassword().equals(userInDatabase.getPassword());
	}

	@RequestMapping(value = "/login")
	public Object login(User user) {
		User userInDatabase = selectByName(user.getName());
		JSONObject jsonObject = new JSONObject();
		if (userInDatabase == null) {
			jsonObject.put("message", "用户不存在！");
		} else if (!comparePassword(user, userInDatabase)) {
			jsonObject.put("message", "密码错误！");
		} else {
			String token = AuthenticationService.getToken(userInDatabase);
			jsonObject.put("token", token);
			jsonObject.put("user", userInDatabase);
		}
		return jsonObject;
	}
}