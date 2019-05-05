package io.github.zsgdyf.ArticleLabel.Action;

import java.util.List;

import io.github.zsgdyf.Article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.zsgdyf.ArticleLabel.ArticleLabel;
import io.github.zsgdyf.ArticleLabel.Mapper.ArticleLabelMapper;
import io.github.zsgdyf.Label.Label;

@RestController
public class ArticleLabelAction {
    @Autowired
    private ArticleLabelMapper articleLabelMapper;

    @RequestMapping(value = "/getLabel")
    public List<Label> getLabel(Integer articleId) {
        return articleLabelMapper.findLabelByArticleId(articleId);
    }

    @RequestMapping(value = "/addLabel")
    public void addLabel(ArticleLabel articleLabel) {
        articleLabelMapper.insert(articleLabel);
    }

    @RequestMapping(value = "/getArticleByLabel")
    public List<Article> getArticleByLabelID(Integer labelId) {
        return articleLabelMapper.getArticleByLabelId(labelId);
    }
}