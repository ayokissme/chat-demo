<template>
    <main class="container">
        <div class="content">

            <div class="user">
                <div class="chat-settings" v-if="multipleChat" @click="copy">
                    <i class="fa-solid fa-copy fa-sm"></i>
                    Invitation link
                </div>

                <img class="chat-image" :src="chatImage" alt="img">
                <a href="#" v-html="chatName"></a>
            </div>

            <messages-list :messages="messages"
                           :conversation="conversation"
                           :avatars="avatars"/>

        </div>
    </main>

    <div class="notification-bar">
        <notification v-for="notification in notifications"
                      :key="notification.notificationId"
                      :notification="notification"
                      :notifications="notifications"/>
    </div>
</template>

<script>

import MessagesList from 'components/messages/MessageList.vue'
import {addHandler} from 'utils/ws'
import Notification from 'components/Notification.vue'

export default {
    components: {
        MessagesList,
        Notification,
    },
    data() {
        return {
            notifications: [],
            messages: [],
            conversation: null,
            avatars: [],
            chatName: '',
            user: frontendData.user,
            chatImage: '',
            multipleChat: false,
        }
    },
    created() {
        addHandler(data => {
            this.messages.push(data)

            if (data.senderId === this.user.id
                || this.conversation.conversationId === data.conversation.conversationId) {
                return
            }
            this.notifications.push(data)
        })
    },

    mounted() {
        if (this.conversation.conversationType === 'ONE_TO_ONE') {
            const otherUser = this.conversation.participants.find(p => p.id !== this.user.id)
            this.chatName = otherUser.username

            const avatarOwner = this.avatars.find(a => a.id === otherUser.id)
            this.chatImage = 'data:image/png;base64,' + avatarOwner.img
        } else {
            console.log(this.conversation)
            const conv = this.avatars.find(a => a.id === this.conversation.conversationId)
            this.chatImage = 'data:image/png;base64,' + conv.img
            this.chatName = this.conversation.name
            this.multipleChat = true
        }
    },
    beforeRouteEnter(to, from, next) {
        function getChatContent() {
            const path = '/api/message/' + to.params['id']
            fetch(path)
                .then(result => result.json())
                .then(data => {
                    next(vm => {
                        vm.messages = data.messages
                        vm.conversation = data.conversation
                        vm.avatars = data.avatars
                    })
                })
                .catch(() => {
                    next('/chat')
                })
        }
        if ('secretKey' in to.params) {
            const addToConvPath = '/api/conversation/' + to.params['id'] + '/' + to.params['secretKey']
            fetch(addToConvPath)
                .then(() => {
                    getChatContent()
                })
                .catch(() => {
                    next('/chat')
                })
        }
        else {
            getChatContent()
        }
    },
    methods: {
        copy() {
            const link = "http://localhost:8081/chat/" + this.conversation.conversationId + "/" + this.conversation.secretKey
            navigator.clipboard.writeText(link);
        },
    }
}
</script>

<style>

.user {
    padding: 15px 0;
    border-bottom: 1px black solid;
    text-align: center;
    font-size: 32px;
}

.user a {
    color: #7328f8;
    text-decoration: none;
}

.user a:hover {
    color: #4f1ab0;
}

.chat-image {
    position: absolute;
    top: 8px;
    right: 10px;
    width: 50px;
    height: 50px;
    border-radius: 50%;
}

.chat-settings {
    position: absolute;
    top: 25px;
    left: 20px;
    color: #7328f8;
    font-size: 18px;
    cursor: pointer;
}

.chat-settings:hover {
    color: #4f1ab0;
}

.chat-settings:active {
    color: #3b1383;
}

</style>