package io.github.zsgdyf.Label.Action;

import java.security.PrivilegedAction;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.zsgdyf.Label.Label;
import io.github.zsgdyf.Label.Mapper.LabelMapper;

@RestController

public class LabelAction{
	@Autowired
	private LabelMapper labelMapper;
	
	@RequestMapping(value = "/popularLabels")
	public List<Label> getAllLabel(){
		return labelMapper.getAllLabel();
	}
}