<template>
    <form class="chat-input" @submit.prevent="sendMessage" name="message">
        <textarea name="content" placeholder="Type something"
                  v-model="content"
                  @keydown.enter.exact.prevent="sendMessage"
                  @keydown.enter.shift.exact.prevent="content += '\n'"/>
        <input :disabled="content.length === 0" type="submit" class="send" value="Send">
    </form>
</template>

<script>

import {sendMessage} from "utils/ws";

export default {
    props: ['messages'],
    data() {
        return {
            content: '',
            user: frontendData.user,
        }
    },
    methods: {
        sendMessage() {
            sendMessage({content: this.content, sender: this.user.id})
            this.content = ''
        }
    }
}
</script>

<style>

.send {
    font-size: 18px;
    width: 100%;
    height: 50px;
    background-color: #914ff5;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.send:hover {
    background-color: #8941fa;
}

.send:active {
    background-color: #7328f8;
}

.send:disabled {
    color: #653f9f;
    background-color: #914ff5;
}

textarea {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;

    padding: 10px;
    width: 100%;
    min-height: 100px;
    resize: none;

    border-radius: 10px;
    border-color: #000000;
    font-size: 18px;
}

textarea:focus {
    outline: none;
}

.chat-input {
    box-sizing: border-box;

    position: absolute;
    padding: 10px;
    display: block;
    bottom: 0;
    left: 0;

    width: 100%;
}

</style>