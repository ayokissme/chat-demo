<template>
    <div class="user-block" @click="getConversationAddress">
        <img class="user-image" :src="image" alt="pic">
        <div class="user-name">{{ user.username }}</div>
    </div>
</template>

<script>
export default {
    name: "SearchResult",
    props: ['user', 'avatars'],
    data() {
        return {
            image: '',
            frontendUser: frontendData.user,
        }
    },
    created() {
        const avatarOwner = this.avatars.find(p => p.id === this.user.id)
        this.image = 'data:image/png;base64,' + avatarOwner.img
    },
    methods: {
        getConversationAddress() {
            const url = "/api/conversation/get/" + this.frontendUser.id + "/" + this.user.id
            fetch(url)
                .then(result => result.json())
                .then(data => {
                    this.$router.push('/chat/' + data.conversationId)
                })
        }
    }
}
</script>

<style scoped>
.user-block {
    position: relative;
    box-sizing: border-box;
    width: 100%;
    height: 100px;
    margin-bottom: 10px;
    background-color: #ffe349;
    border-radius: 10px;
    cursor: pointer;
}

.user-block:hover {
    background-color: rgba(238, 206, 36, 0.96);
}

.user-image {
    position: absolute;
    top: 15px;
    left: 25px;
    width: 70px;
    height: 70px;
    border-radius: 50%;
}

.user-name {
    position: absolute;
    top: 30px;
    left: 120px;
    font-weight: bold;
    font-size: 32px;
}
</style>