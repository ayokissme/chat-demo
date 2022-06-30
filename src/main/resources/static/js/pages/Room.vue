<template>
    <main class="container">
        <div class="content">

            <div class="user">
                <a href="#">Username</a>
            </div>
            <messages-list :messages="messages"/>

        </div>
    </main>
</template>

<script>

import MessagesList from 'components/chat-room/MessageList.vue'
import {addHandler} from 'utils/ws'

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
        addHandler(data => {
            this.messages.push(data)
        })
    },
    beforeRouteEnter(to, from, next) {
        const path = '/api/message/' + to.params['id']
        fetch(path)
            .then(result => result.json())
            .then(data => {
                next(vm => {
                    vm.messages = data;
                })
            })
            .catch(() => {
                next('/chat')
            })
    }
}
</script>

<style>

.user {
    padding: 15px;
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

</style>