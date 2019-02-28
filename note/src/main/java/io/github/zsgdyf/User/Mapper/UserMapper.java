package io.github.zsgdyf.User.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.github.zsgdyf.User.User;

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

	@Select("select * from User where name=#{name}")
	User selectByName(String Name);

	@Select("select * from User where id=#{id} and password=#{password}")
	User login(Integer id, String password);
}