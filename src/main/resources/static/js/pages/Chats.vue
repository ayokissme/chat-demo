<template>
    <main class="container">
        <div style="background-color: white; min-height: 100%; height: auto; padding: 30px;">
            <chat-list :chats="chats"/>
        </div>
    </main>
</template>

<script>

import {addHandler} from 'utils/ws'
import ChatList from 'components/chat-list/ChatList.vue'

export default {
    components: {
        ChatList
    },
    data() {
        return {
            chats: [],
        }
    },
    created() {
        fetch("/api/conversation/all")
            .then(result => result.json())
            .then(data => {
                this.chats = data
            })

        addHandler(data => {
            console.log(data)
            // this.messages.push(data)
        })
    }
}

</script>

<style scoped>
</style>