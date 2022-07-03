<template v-cloak>

    <chat-search/>

    <div v-show="showChatBlock">
        <div v-if="chats.length !== 0">
            <chat-row v-for="chat in chats"
                      :chat="chat"
                      :key="chat.conversationId"
                      :avatars="avatars"/>
        </div>

        <div v-else>
            <h1 style="text-align: center">Empty</h1>
        </div>
    </div>

</template>

<script>

import ChatRow from "components/chats/ChatRow.vue";
import ChatSearch from "components/chats/ChatSearch.vue";

export default {
    props: ['chats', 'avatars'],
    components: {
        ChatRow,
        ChatSearch
    },
    data() {
        return {
            chat: null,
            showChatBlock: true,
        }
    }
}

</script>

<style scoped></style>