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
        fetch("/api/message/all")
            .then(result => result.json())
            .then(data => {
                this.messages = data
            })

        addHandler(data => {
            console.log(data)
            this.messages.push(data)
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

.chat {
    position: relative;

    height: 100%;
    background-color: #f6f6f6;
    border: 1px solid #ccc;

    font: 16px/26px Georgia, Garamond, Serif;
    font-size: 18px;
}

html {
    margin: 0;
    padding: 0;
}

body {
    background-color: rgba(145, 79, 245, 0.26);
}

.container {
    display: block;
    max-width: 800px;
    height: 95vh;
    margin: auto;
}

</style>