import {createApp} from 'vue'
import {connect} from './utils/ws'
import router from "./router/router.js";
import App from "pages/App.vue";


connect()
let app = createApp(App)
app.use(router)
app.mount('#app')