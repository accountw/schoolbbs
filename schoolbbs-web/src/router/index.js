import Vue from "vue";
import VueRouter from "vue-router";
import Index from "../views/Index.vue";
import Home from "../components/topic/home.vue";
import Register from "../views/Register";
import Block from "../components/block/Block";
import Cplate from "../components/plate/cplate";
import Topic from "../components/topic/Topic";
import store from "../store/index";
import UserIndex from "../components/user/UserIndex";
import Usersetting from "../components/user/Usersetting";
import Manager from "../views/Manager";
import Collect from "../components/collect/Collect";
import Messages from "../components/message/Messages";
import Message from "element-ui/packages/message/src/main";
import UserManager from "../components/manager/UserManager";
import PlateManager from "../components/manager/PlateManager";
import AdminManager from "../components/manager/AdminManager";
import SchoolRegisterManager from "../components/manager/SchoolRegisterManager";
import Userban from "../components/manager/Userban";
import AddPlate from "../components/manager/AddPlate";
import PlateList from "../components/manager/PlateList";
import TopicManager from "../components/manager/TopicManager";
import SearchResult from "../components/search/SearchResult";
import Findpassword from "../components/login/Findpassword";
import CricleUser from "../components/cricle/CricleUser";
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
        path: "/SearchResult/:context",
        component: SearchResult
      },
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
        path: "/cricle",
        name: "/cricle",
        component: CricleUser
      },
      {
        path: "/plate/:plateid/:index",
        component: Cplate
      },
      {
        path: "/topic/:topicid/:index",
        component: Topic
      },
      {
        path: "/user/:userid",
        component: UserIndex
      },
      {
        path: "/userset/:userid",
        component: Usersetting
      },
      {
        path: "/collect",
        component: Collect
      },
      {
        path: "/message",
        component: Messages
      }
    ]
  },
  {
    path: "/register",
    name: "register",
    component: Register
  },
  {
    path: "/manager",
    name: "/manager",
    component: Manager,
    redirect: "/usermanager",
    children: [
      {
        path: "/usermanager",
        component: UserManager,
        redirect: "/userban",
        children: [
          {
            path: "/schoolregistermanager",
            component: SchoolRegisterManager
          },
          {
            path: "/userban",
            component: Userban
          }
        ]
      },
      {
        path: "/adminmanager",
        component: AdminManager
      },
      {
        path: "/platemanager",
        component: PlateManager,
        redirect: "/platelist",
        children: [
          {
            path: "/platelist",
            component: PlateList
          },
          {
            path: "/addplate",
            component: AddPlate
          }
        ]
      },
      {
        path: "/topicmanager",
        component: TopicManager
      }
    ]
  },
  {
    path: "/findpassword",
    component: Findpassword
  }
];

const router = new VueRouter({
  routes,
  mode: "history"
});
router.beforeEach((to, from, next) => {
  if (
    to.path == "/home" ||
    to.path == "/register" ||
    to.path == "/manager" ||
    to.path == "/findpassword"
  ) {
    next();
  } else {
    if (!store.state.Authorization) {
      Message({
        showClose: true,
        message: "请先登录",
        type: "error"
      });
      next("/home");
    } else {
      next();
    }
  }
});
export default router;
