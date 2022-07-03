<template>
    <div class="block">
        <input class="search" type="text" placeholder="Find user" @keyup.enter="findUser" v-model="username">
        <input class="search-submit" type="submit" value="Search" @click="findUser" :disabled="username.length === 0">
    </div>

    <div v-show="!$parent.$data.showChatBlock">
        <h1>Results: </h1>
        <search-result v-for="user in users"
                       :user="user"
                       :avatars="avatars"/>
    </div>
</template>

<script>

import SearchResult from "components/chats/SearchResult.vue";

export default {
    name: "ChatSearch",
    components: {
        SearchResult,
    },
    data() {
        return {
            username: '',
            users: [],
            avatars: []
        }
    },
    methods: {
        findUser() {
            this.$parent.$data.showChatBlock = false
            const url = "/api/user/find/" + this.username
            fetch(url)
                .then(result => result.json())
                .then(data => {
                    this.users = data.users
                    this.avatars = data.avatars
                })
        },
    },
    watch: {
        username() {
            if (this.username.length === 0) {
                this.$parent.$data.showChatBlock = true
                this.users = []
            }
        },
    }
}
</script>

<style scoped>

.block {
    margin-bottom: 10px;
    width: 100%;
    height: 50px;
}

.search {
    width: 90%;
    height: 100%;
    box-sizing: border-box;
    padding: 10px;
    border-color: #4f1ab0;
    font-size: 16px;
    border-top-left-radius: 10px;
    border-bottom-left-radius: 10px;
    border-right: none;
}

.search:focus {
    outline: none;
}

.search-submit {
    height: 100%;
    color: white;
    font-weight: bold;
    background-color: #914ff5;
    padding: 10px;
    width: 10%;
    border-top-right-radius: 10px;
    border-bottom-right-radius: 10px;
    border: none;
    cursor: pointer;
}

.search-submit:hover {
    background-color: #6d3bbb;
}

.search-submit:disabled {
    color: #653f9f;
    background-color: #914ff5;
    cursor: default;
}

</style>