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
    id: localStorage.getItem("id") ? localStorage.getItem("id") : ""
  },
  mutations: {
    Login(state, data) {
      state.Authorization = data.Authorization;
      localStorage.setItem("Authorization", data.Authorization);
      state.username = data.username;
      localStorage.setItem("username", data.username);
      state.expire = new Date().getTime() + 1800000;
      localStorage.setItem("expire", state.expire);
    },
    logout(state) {
      localStorage.clear();
      state.username = null;
      state.Authorization = "";
      state.expire = 0;
    },
    setid(state, id) {
      state.id = id;
      localStorage.setItem("id", id);
    }
  },
  actions: {},
  modules: {}
});
