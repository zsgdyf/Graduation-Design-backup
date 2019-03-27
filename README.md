# Graduation-Design-backup
:label:毕业设计项目的备份
## 基于 Vue.js 和 Spring Boot 的笔记分享平台
### To-Do List：

- [x] 文章提交

将前端发送的 JSON 格式的字符串解析为 Java Bean 对象：

使用 Alibaba 的 `fastjson` 库

```java
Article article2 = JSON.parseObject(article,Article.class);
```

- [x] 笔记列表展示

（element ui 的时间轴没法按时间戳排序……坑）

- [ ] 用户信息显示
