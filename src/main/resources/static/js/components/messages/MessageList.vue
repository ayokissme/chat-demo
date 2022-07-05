<template>
    <div class="messages" id="messages">
        <div v-if="messages.length === 0" class="no-messages">
            Your messages will be here
        </div>
        <div v-else>
            <message-row v-for="message in messages"
                         :key="message.id"
                         :message="message"
                         :conversation="conversation"
                         :avatars="avatars"/>
        </div>
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

.no-messages {
    margin-top: 150px;
    text-align: center;
    font-size: 32px;
    color: #949292;
}
</style>