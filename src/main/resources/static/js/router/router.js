import { createWebHistory, createRouter } from "vue-router";
import Room from 'pages/Room.vue'
import Chats from 'pages/Chats.vue'

const routes = [
    {path: '/chat', component: Chats},
    {path: '/chat/:id', component: Room},
    // {path: '*', component: ChatList},
]

export default createRouter({
    history: createWebHistory(),
    routes,
})


