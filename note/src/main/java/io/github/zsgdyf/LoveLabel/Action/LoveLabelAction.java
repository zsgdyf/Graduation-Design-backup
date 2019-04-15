package io.github.zsgdyf.LoveLabel.Action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.zsgdyf.Label.Label;
import io.github.zsgdyf.LoveLabel.Mapper.LoveLabelMapper;

@RestController
public class LoveLabelAction{
	@Autowired
	private LoveLabelMapper loveLabelMapper;
	
	@RequestMapping(value = "/getUserLabel")
	List<Label> getUserLabel(Integer userId) {
		return loveLabelMapper.findLabelByUserId(userId);
	}
}