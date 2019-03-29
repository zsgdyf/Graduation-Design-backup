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

- [ ] 用户信息显示
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
