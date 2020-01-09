import Vue from "vue";
import VueRouter from "vue-router";
import Index from "../views/Index.vue";
import Topic from "../components/Topic.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    redirect: "/index"
  },
  {
    path: "/index",
    name: "index",
    component: Index,
    children: [
      {
        path: "/topic",
        name: "/topic",
        component: Topic
      }
    ]
  }
];

const router = new VueRouter({
  routes
});

export default router;
