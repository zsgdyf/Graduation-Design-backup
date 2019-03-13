<template>
  <div id="myNotes">
    <NavMenu></NavMenu>
    <div id="content">
      <el-row
        type="flex"
        class="row-bg"
        justify="center"
      >
        <SideNavMenu></SideNavMenu>
        <el-timeline>
          <el-timeline-item
            timestamp="2019/3/13"
            placement="top"
          >
            <el-card>
              <h4>更新 Github 模板</h4>
              <p>zsgdyf 提交于 2019/3/13 20:46</p>
            </el-card>
          </el-timeline-item>
          <el-timeline-item
            timestamp="2019/3/3"
            placement="top"
          >
            <el-card>
              <h4>更新 Github 模板</h4>
              <p>zsgdyf 提交于 2019/3/3 20:46</p>
            </el-card>
          </el-timeline-item>
          <el-timeline-item
            timestamp="2019/2/28"
            placement="top"
          >
            <el-card>
              <h4>更新 Github 模板</h4>
              <p>zsgdyf 提交于 2019/2/28 20:46</p>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </el-row>
    </div>
  </div>
</template>

<script>
import NavMenu from '@/components/NavMenu.vue'
import SideNavMenu from '@/components/SideNavMenu.vue'
export default {
  components: {
    NavMenu,
    SideNavMenu
  }
}
</script>

<style scoped>
#myNotes {
  width: 1140px;
  margin: 0 auto;
}
#content {
    margin-top: 50px;
}
.el-timeline {
  width: 900px;
}
</style>
