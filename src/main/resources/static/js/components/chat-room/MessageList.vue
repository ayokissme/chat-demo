<template>
    <div class="messages">
        <message v-for="message in messages"
                 :key="message.id"
                 :message="message"/>
        <chat-form :messages="messages" :conversation="conversation"/>
    </div>
</template>

<script>

import Message from 'components/chat-room/Message.vue'
import ChatForm from 'components/chat-room/ChatForm.vue'

export default {
    props: ['messages', 'conversation'],
    components: {
        Message,
        ChatForm
    },
    data() {
        return {
            message: null
        }
    }
}
</script>

<style>
.messages {
    padding: 20px;
    height: 63%;
    overflow: auto;
}
</style>