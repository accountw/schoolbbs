<template>
  <div class="Picture">
    <ul id="ul">
      <li v-for="picture in pictures" :key="picture">
        <el-image
          style="width: 400px;height: 300px"
          :src="url(picture)"
          :preview-src-list="srclist"
          :fit="v"
        >
        </el-image>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "Picture",
  props: ["picture"],
  data() {
    return {
      v: "contain"
    };
  },
  computed: {
    pictures() {
      return this.picture.split(",");
    },
    srclist() {
      var l = [];
      for (var x of this.picture.split(",")) {
        l.push("/image/" + x);
      }
      return l;
    }
  },
  methods: {
    url(picture) {
      return "/image/" + picture;
    }
  }
};
</script>

<style scoped>
#ul {
  list-style: none;
  padding-inline-start: 0;
}
</style>
