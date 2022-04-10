<template>
  <div>
    <sidebar-component></sidebar-component>
    <div class="relative md:ml-64 bg-blueGray-100">
      <!-- <div class="relative bg-blueGray-100"> -->
      <router-view />
    </div>
  </div>
</template>

<script>
import SidebarComponent from "@/components/Sidebar.vue";
export default {
  name: "App",
  components: {
    SidebarComponent,
  },
  data() {
    return {};
  },
};
</script>
