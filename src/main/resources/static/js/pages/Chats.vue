<template>
    <main class="container">
        <div style="background-color: white; min-height: 95%; height: auto; padding: 30px;">
            <chat-list :chats="chats"
                       :avatars="avatars"/>
        </div>
    </main>
</template>

<script>

import {addHandler} from 'utils/ws'
import ChatList from 'components/chats/ChatList.vue'

export default {
    components: {
        ChatList,
    },
    data() {
        return {
            chats: [],
            avatars: [],
            notifications: [],
        }
    },
    created() {
        addHandler(data => {
            const conversation = data.conversation
            const chatInList = this.chats.find(c => conversation.conversationId === c.conversationId)
            if (chatInList === undefined) {
                this.chats.push(conversation)
            } else {
                const index = this.chats.findIndex(c => conversation.conversationId === c.conversationId)
                this.chats.splice(index, 1)
                this.chats.unshift(conversation)
            }
        })
    },
    beforeRouteEnter(to, from, next) {
        fetch("/api/conversation/all")
            .then(result => result.json())
            .then(data => {
                next(vm => {
                    vm.chats = data.conversations
                    vm.avatars = data.avatars
                })
            })
    },
}

</script>

<style scoped>
</style>