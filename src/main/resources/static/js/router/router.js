import {createWebHistory, createRouter} from "vue-router";
import Room from 'pages/Messages.vue'
import Settings from 'pages/Settings.vue'
import Chats from 'pages/Chats.vue'
import NotFound from 'pages/NotFound.vue'

const routes = [
    {path: '/:pathMatch(.*)*', name: 'NotFound', component: NotFound},
    {path: '/chat', component: Chats},
    {path: '/settings', component: Settings},
    {path: '/chat/:id', component: Room},
]

export default createRouter({
    history: createWebHistory(),
    routes,
})


