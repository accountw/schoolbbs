import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    username: localStorage.getItem("username")
      ? localStorage.getItem("username")
      : null,
    Authorization: localStorage.getItem("Authorization")
      ? localStorage.getItem("Authorization")
      : "",
    expire: localStorage.getItem("expire") ? localStorage.getItem("expire") : 0,
    id: localStorage.getItem("id") ? localStorage.getItem("id") : "",
    role: localStorage.getItem("role") ? localStorage.getItem("role") : "",
    isRefreshing: "1",
    refreshSubscribers: []
  },
  mutations: {
    Login(state, data) {
      state.Authorization = data.Authorization;
      localStorage.setItem("Authorization", data.Authorization);
      state.username = data.username;
      localStorage.setItem("username", data.username);
      state.expire = new Date().getTime() + 1800000;
      localStorage.setItem("expire", state.expire);
      state.role = data.role;
      localStorage.setItem("role", data.role);
    },
    logout(state) {
      localStorage.clear();
      state.username = null;
      state.Authorization = "";
      state.expire = 0;
      state.role = "";
    },
    setid(state, id) {
      state.id = id;
      localStorage.setItem("id", id);
    },
    refreshSubscribersPush(state, a) {
      state.refreshSubscribers.push(a);
    }
  },
  actions: {},
  modules: {}
});
