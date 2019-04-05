package io.github.zsgdyf.LoveArticle;


import io.github.zsgdyf.Article.Article;

public class LoveArticle {
	private Integer user_id;
	private Integer article_id;
	private String love_time;
	private String love_date;
	/**
	 * @return the user_id
	 */
	public Integer getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the article_id
	 */
	public Integer getArticle_id() {
		return article_id;
	}
	/**
	 * @param article_id the article_id to set
	 */
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	/**
	 * @return the love_time
	 */
	public String getLove_time() {
		return love_time;
	}
	/**
	 * @param love_time the love_time to set
	 */
	public void setLove_time(String love_time) {
		this.love_time = love_time;
	}
	/**
	 * @return the love_date
	 */
	public String getLove_date() {
		return love_date;
	}
	/**
	 * @param love_date the love_date to set
	 */
	public void setLove_date(String love_date) {
		this.love_date = love_date;
	}
}