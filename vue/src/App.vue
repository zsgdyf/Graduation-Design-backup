<template>
  <div id="app">
    <transition
      name="el-fade-in-linear"
      mode="out-in"
      appear
    >
      <router-view />
    </transition>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
html,
body {
  height: 100%;
}
body {
  margin: 0;
  background-color: #f4f5f5;
}
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center; */
  width: 100%;
}
a {
  text-decoration: none;
  color: inherit;
}
</style>
