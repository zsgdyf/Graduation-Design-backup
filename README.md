# Graduation-Design-backup
:label:毕业设计项目的备份
## 基于 Vue.js 和 Spring Boot 的笔记分享平台
### To-Do List：

- [x] 文章提交

将前端发送的 JSON 格式的字符串解析为 Java Bean 对象：

使用 Alibaba 的 `fastjson` 库。

```java
String article = "{"xxx": "abcd"}";
Article article2 = JSON.parseObject(article,Article.class);
```

- [x] 笔记列表展示

将用户名通过 `GET` 发送给后台进行数据库查询作者为该用户名的所有文章。

（element ui 的时间轴没法按时间戳排序……。）

使用 `index` 排序，参考官方文档的例子增加一个正序和倒序切换的开关。

- [x] 笔记详情页面

通过这种方法给文章页面传递文章 ID 参数：

```javascript
toArticles (articleId) {
      this.$router.push({
        path: '/articles',
        query: {
          id: articleId
        }
      })
    }
```
文章页面接收参数并发送 `GET` 请求

```javascript
axios.get('http://localhost:8080/articles?id=' + this.$route.query.id)
```

使用 [vue-markdown](https://github.com/miaolz123/vue-markdown) 来渲染文章

- [x] 删除和存入草稿箱功能

给文章实体 `article` 增加一个状态属性 `state`，为“published”时候表示发布，为“deleted”时候表示删除，为“draft”时候表示是草稿。

```java
@Update("update article set state='deleted' where id=#{id}")
Integer delete(Integer id);

@Select("select * from article where author=#{author} and state not in('deleted')")
List<Article> selectAll(String author);

@Select("select * from article where id=#{id}")
Article selectOne(Integer id);
```

- [x] 首页文章显示

- [x] 文章详情页面添加收藏功能

可切换的文本链接，可以在「收藏」和「已收藏」之间切换

```javascript
<span
@click="loveArticle"
class="action"
v-show="love"
>收藏</span>
<span
@click="cancelLoveArticle"
class="action"
v-show="loved"
>已收藏</span>
```

- [ ] 注册后选择感兴趣的标签

- [ ] 发布按钮组

- [x] 我的收藏页面

通过建立用户和笔记的收藏关系表，方便查询某个用户收藏的所有文章

```java
@Select("select * from article where id in "
	+ "(select article_id from love_article where user_id = #{user_id})")
List<Article> select(Integer user_id);
```

- [ ] 笔记编辑功能

- [x] 用户信息显示

包括用户名、头像、个人简介、感兴趣的标签等等

需要在数据库中添加相应字段

- [x] 标签组件

在文章底部和用户个人中心显示标签，标签添加入口暂定

- [ ] 首页文章收藏量显示

- [ ] 热门标签下显示文章

- [ ] 首页文章部分内容

- [ ] 用户新增标签（笔记创建时）

- [ ] 评论组件

# License

**All Rights Reserved**
