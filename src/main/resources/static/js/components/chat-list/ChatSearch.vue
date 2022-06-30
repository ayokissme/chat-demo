<template>
    <div class="block">
        <input class="search" type="text" placeholder="Find user" @keyup.enter="findUser" v-model="username">
        <input class="search-submit" type="submit" value="Search" @click="findUser">
    </div>
</template>

<script>
export default {
    name: "ChatSearch",
    data() {
        return {
            username: '',
        }
    },
    methods: {
        findUser() {
            console.log("wherar u", this.username)
        }
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
}

.search-submit:hover {
    background-color: #6d3bbb;
}

</style>