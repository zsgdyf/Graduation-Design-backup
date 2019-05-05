package io.github.zsgdyf.Label.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import io.github.zsgdyf.Label.Label;
import org.springframework.stereotype.Service;

@Mapper
@Service
public interface LabelMapper {
	@Insert("insert into label (id, content) values (#{id}, #{content})")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(Label label);
	
	@Select("select * from label")
	List<Label> getAllLabel();
}