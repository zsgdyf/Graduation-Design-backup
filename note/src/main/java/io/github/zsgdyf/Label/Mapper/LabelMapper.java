package io.github.zsgdyf.Label.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import io.github.zsgdyf.Label.Label;

@Mapper
public interface LabelMapper {
	@Insert("insert into label (id, content) values (#{id}, #{content})")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(Label label);
}