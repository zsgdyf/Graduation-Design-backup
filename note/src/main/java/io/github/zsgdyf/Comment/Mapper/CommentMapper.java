package io.github.zsgdyf.Comment.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import io.github.zsgdyf.Comment.Comment;
import org.springframework.stereotype.Service;

@Mapper
@Service
public interface CommentMapper {
	@Select("insert into comment (content, author, createTime,articleId) "
			+ "values (#{content},#{author},#{createTime},#{articleId})")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(Comment comment);

	@Delete("delete from comment where id=#{id}")
	void delete(Integer id);
	
	@Select("select * from comment")
	List<Comment> selectAll();
	
	@Select("select * from comment where articleId = #{articleId}")
	List<Comment> selectCommentByArticleId(Integer articleId);
}