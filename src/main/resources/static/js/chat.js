import {createApp} from 'vue'
import VueResource from 'vue-resource'
import ChatApp from "pages/ChatApp.vue";

let app = createApp(ChatApp)
    // .mount('#app')
// app.use(VueResource)
console.log(app.use())
