import {createWebHistory, createRouter} from "vue-router";
import Room from 'pages/Messages.vue'
import Settings from 'pages/Settings.vue'
import CreateChat from 'pages/CreateChat.vue'
import Chats from 'pages/Chats.vue'
import NotFound from 'pages/NotFound.vue'

const routes = [
    {path: '/:pathMatch(.*)*', name: 'NotFound', component: NotFound},
    {path: '/chat', component: Chats},
    {path: '/chat/:id', component: Room},
    {path: '/chat/:id/:secretKey', component: Room},
    {path: '/settings', component: Settings},
    {path: '/new-chat', component: CreateChat},
]

export default createRouter({
    history: createWebHistory(),
    routes,
})


