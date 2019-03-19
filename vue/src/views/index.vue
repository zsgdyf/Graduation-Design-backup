<template>
  <div id="index">
    <NavMenu></NavMenu>
    <el-row>
      <el-col :span="12">
        <el-card shadow="always">
          <h1>笔记1</h1>
          <p>
            Elit simul nonumes et per, iisque nominati id vel. Sed scaevola qualisque at. Vim option delectus probatus ea. Sea ex error iracundia definitiones. Vim et nostro prodesset, has lucilius disputationi cu, suas eros dicta usu id.</p>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="always">
          <h1>笔记2</h1>
          <p>Lorem ipsum是指一篇用于网页设计、排印、布局和印刷的伪拉丁文章，其用于代替英语去强调设计元素而不是内容。它也被称为占位符文（或填充文）。它是一个很便利的模板工具。它用于帮助编排文章或演示文稿的视觉元素，如排印，字体，或布局。Lorem ipsum 大多是由古典作家和哲学家西塞罗创作的拉丁文的一部分。它的单词和字母由于添加或去移除而被改变了，所以故意使其内容荒谬；它不是真实的，正确的，再也不是可理解的拉丁语。虽然lorem ipsum看起来仍像古典拉丁语，但实际上它没有任何意义。因为西塞罗的文本不包含K，W，Z 这几个有异于拉丁文的字母，所以这几个字母和其他一些字母常常被随机插入去模拟欧洲语言的排印样式，这些字在原文中其实并没有。</p>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="always">
          <h1>笔记3</h1>
          <p>LLorem ipsum是指一篇用于网页设计、排印、布局和印刷的伪拉丁文章，其用于代替英语去强调设计元素而不是内容。它也被称为占位符文（或填充文）。它是一个很便利的模板工具。它用于帮助编排文章或演示文稿的视觉元素，如排印，字体，或布局。Lorem ipsum 大多是由古典作家和哲学家西塞罗创作的拉丁文的一部分。它的单词和字母由于添加或去移除而被改变了，所以故意使其内容荒谬；它不是真实的，正确的，再也不是可理解的拉丁语。虽然lorem ipsum看起来仍像古典拉丁语，但实际上它没有任何意义。因为西塞罗的文本不包含K，W，Z 这几个有异于拉丁文的字母，所以这几个字母和其他一些字母常常被随机插入去模拟欧洲语言的排印样式，这些字在原文中其实并没有。

            Nonumes habemus ea qui, pro diam fugit accusam ei, ei labores delicata vel. Cu vim inani debet ancillae, ut duo agam harum disputationi. Nec purto nostrud voluptua ea, alienum ocurreret complectitur ius eu. Id quo eruditi molestie. At legere possim menandri has, delenit maiorum persequeris sea id, insolens consequat mei at.</p>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="always">
          <h1>笔记4</h1>
          <p>Nonumes habemus ea qui, pro diam fugit accusam ei, ei labores delicata vel. Cu vim inani debet ancillae, ut duo agam harum disputationi. Nec purto nostrud voluptua ea, alienum ocurreret complectitur ius eu. Id quo eruditi molestie. At legere possim menandri has, delenit maiorum persequeris sea id, insolens consequat mei at.</p>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import NavMenu from '@/components/NavMenu.vue'
export default {
  components: {
    NavMenu
  }
}
</script>
<style scoped>
#index {
    width: 1140px;
    margin: 0 auto;
}
.el-card {
  margin: 10px;
  text-align: left;
  height: 220px;
}
h1 {
  font-size: 16px;
}
</style>
