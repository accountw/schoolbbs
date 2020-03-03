import Vue from "vue";
import VueRouter from "vue-router";
import Index from "../views/Index.vue";
import Home from "../components/topic/home.vue";
import Register from "../views/Register";
import Plate from "../components/plate/Plate";

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
        path: "/plate",
        name: "/plate",
        component: Plate
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
