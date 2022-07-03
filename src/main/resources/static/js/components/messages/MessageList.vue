<template>
    <div class="messages" id="messages">
        <message-row v-for="message in messages"
                 :key="message.id"
                 :message="message"
                 :conversation="conversation"
                 :avatars="avatars"/>
        <chat-form :messages="messages" :conversation="conversation"/>
    </div>
</template>

<script>

import MessageRow from 'components/messages/MessageRow.vue'
import ChatForm from 'components/messages/ChatForm.vue'

export default {
    props: ['messages', 'conversation', 'avatars'],
    components: {
        MessageRow,
        ChatForm
    },
    data() {
        return {
            message: null
        }
    },

}
</script>

<style>
.messages {
    padding: 20px;
    height: 63%;
    overflow: auto;
}
</style>