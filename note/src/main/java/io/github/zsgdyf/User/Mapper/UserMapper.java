package io.github.zsgdyf.User.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.github.zsgdyf.User.User;
import org.springframework.stereotype.Service;

@Mapper
@Service
public interface UserMapper {
    @Insert("insert into user (name, password) values(#{name},#{password})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(User user);

    @Update("update user set name=#{name},password=#{password} where id=#{id}")
    Integer update(User user);

    @Delete("delete from user where id=#{id}")
    Integer delete(Integer id);

    @Select("select * from user")
    List<User> selectAll();

    @Select("select * from user where id=#{id}")
    User selectById(Integer id);

    @Select("select * from user where name=#{name}")
    User selectByName(String Name);

    @Select("select * from user where id=#{id} and password=#{password}")
    User login(Integer id, String password);
}