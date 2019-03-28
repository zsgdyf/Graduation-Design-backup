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

使用 [vue-markdown](https://github.com/miaolz123/vue-markdown)来渲染文章
