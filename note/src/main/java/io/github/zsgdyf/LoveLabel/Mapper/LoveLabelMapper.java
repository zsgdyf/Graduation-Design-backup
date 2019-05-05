package io.github.zsgdyf.LoveLabel.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.github.zsgdyf.Label.Label;
import org.springframework.stereotype.Service;

@Mapper
@Service
public interface LoveLabelMapper {
	@Select("select * from label where id in "
			+ "(select label_id from love_label where user_id = #{userId})")
	List<Label> findLabelByUserId(Integer userId);
}