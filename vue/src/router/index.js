import {
  createRouter,
  createWebHistory,
  createWebHashHistory,
} from "vue-router";

const routes = [
  {
    path: "/login",
    name: "HomeView",
    component: () => import("@/views/HomeView"),
    children: [
      { path: "/home", name: "Home", component: () => import("@/views/Home") },
      { path: "/user", name: "User", component: () => import("@/views/User") },
      {
        path: "/course",
        name: "Course",
        component: () => import("@/views/Course"),
      },
    ],
  },
  {
    path: "/",
    name: "Login",
    component: () => import("@/views/Login"),
  },
  {
    path: "/register",
    name: "Register",
    component: () => import("@/views/Register"),
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
