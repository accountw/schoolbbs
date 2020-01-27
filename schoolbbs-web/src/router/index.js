import Vue from "vue";
import VueRouter from "vue-router";
import Index from "../views/Index.vue";
import Topic from "../components/topic/Topic.vue";
import Register from "../views/Register";

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
  },
  {
    path: "/register",
    name: "register",
    component: Register
  }
];

const router = new VueRouter({
  routes,
  mode: "history"
});

export default router;
