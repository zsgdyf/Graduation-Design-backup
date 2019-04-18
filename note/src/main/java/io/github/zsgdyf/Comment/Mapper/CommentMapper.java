package io.github.zsgdyf.Comment.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import io.github.zsgdyf.Comment.Comment;

@Mapper
public interface CommentMapper {
	@Select("insert into comment (content, author, createTime) "
			+ "values (#{content},#{author},#{createTime})")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(Comment comment);

	@Delete("delete from comment where id=#{id}")
	void delete(Integer id);
}