import Vue from "vue";
import VueRouter from "vue-router";
import Index from "../views/Index.vue";
import Home from "../components/topic/home.vue";
import Register from "../views/Register";
import Block from "../components/block/Block";
import Cplate from "../components/plate/cplate";
import Topic from "../components/topic/Topic";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    redirect: "/home"
  },
  {
    path: "/index",
    name: "index",
    component: Index,
    children: [
      {
        path: "/home",
        name: "/home",
        component: Home
      },
      {
        path: "/block",
        name: "/block",
        component: Block
      },
      {
        path: "/plate/:plateid",
        component: Cplate
      },
      {
        path: "/topic/:topicid",
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
