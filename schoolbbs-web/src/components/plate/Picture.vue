<template>
  <div class="Picture">
    <span v-for="picture in pictures" :key="picture" id="image">
      <el-image
        style="width: 150px;height: 150px;border: 1px solid #ebeef5;margin-right: 8px"
        :src="url(picture)"
        :preview-src-list="srclist"
        :fit="v"
      >
      </el-image>
    </span>
  </div>
</template>

<script>
export default {
  name: "Picture",
  props: ["picture"],
  data() {
    return {
      v: "fill"
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

</style>
