package io.zsgdyf.github.User.Action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.zsgdyf.github.User.User;
import io.zsgdyf.github.User.Mapper.UserMapper;

@RestController
public class UserAction {
	@Autowired
	private UserMapper UserMapper;

	@RequestMapping(value = "/insert")
	public void insert(User User) {
		UserMapper.insert(User);
	}

	@RequestMapping(value = "/select")
	public User selectById(@RequestParam Integer id) {
		return UserMapper.selectById(id);
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
}