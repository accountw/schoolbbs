<template>
  <div class="Plate"></div>
</template>

<script>
import { getplate } from "../../network/plate";
export default {
  name: "Plate",
  data() {
    return {
      plate: null
    };
  },
  created() {
    this.get();
  },
  methods: {
    get() {
      getplate()
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.plate = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
</script>

<style scoped></style>
