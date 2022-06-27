<template>
    <main class="container">
        <div class="chat">

            <div class="user">
                <a href="#">Username</a>
            </div>
            <messages-list :messages="messages"/>

        </div>
    </main>
</template>

<script>

import MessagesList from 'components/chat-room/MessageList.vue'

export default {
    components: {
        MessagesList,
    },
    data() {
        return {
            messages: [],
        }
    },
    created() {
        fetch("/api/message/all")
            .then(result => result.json())
            .then(data => {
                this.messages = data
            })
    }
}
</script>