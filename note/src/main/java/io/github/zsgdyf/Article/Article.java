package io.github.zsgdyf.Article;

public class Article{
	private Integer id;
	private String title;
	private String content;
	private String content_md;
	private String author;
	private String create_time;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the content_md
	 */
	public String getContent_md() {
		return content_md;
	}
	/**
	 * @param content_md the content_md to set
	 */
	public void setContent_md(String content_md) {
		this.content_md = content_md;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the creat_time
	 */
	public String getCreate_time() {
		return create_time;
	}
	/**
	 * @param creat_time the creat_time to set
	 */
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
}