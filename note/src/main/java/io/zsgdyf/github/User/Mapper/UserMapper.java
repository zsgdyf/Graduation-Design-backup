package io.zsgdyf.github.User.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;

import io.zsgdyf.github.User.User;

@Mapper
public interface UserMapper {
	@Insert("insert into User (name, password) values(#{name},#{password})")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(User User);

	@Update("update User set name=#{name},password=#{password} where id=#{id}")
	Integer update(User User);

	@Delete("delete from User where id=#{id}")
	Integer delete(Integer id);

	@Select("select * from User")
	List<User> selectAll();

	@Select("select * from User where id=#{id}")
	User selectById(Integer id);
}