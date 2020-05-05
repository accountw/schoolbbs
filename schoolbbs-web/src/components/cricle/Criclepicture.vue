<template>
  <div class="Criclepicture" >
     <el-row>
         <el-col :span="7" v-for="picture in pictures" :key="picture" >
             <el-image
                     style="width: 200px;height: 200px;border: 1px solid #dcdfe6;margin-right: 3px"
                     :src="url(picture)"
                     :preview-src-list="srclist"
                     :fit="v">
             </el-image>
         </el-col>
     </el-row>
  </div>
</template>

<script>
export default {
  name: "Criclepicture",
  props: ["picture"],
  data() {
    return {
      v: "cover"
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
