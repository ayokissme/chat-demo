<template>
    <div class="card">
        <div class="card-content">
            <div class="card-img">
                <img :src="userImage" alt="img">
            </div>
            <div class="card-text">
                <span class="card-user" v-html="usernameText"></span>
                <span class="card-message" v-html="message"></span>
            </div>
            <div class="right-block">
                <i class="fa-solid fa-check fa-xl check" ref="icon"></i>
                <span class="date">{{ chat.lastMessageCreatedAt }}</span>
            </div>
        </div>
        <a :href="'/chat/'+ chat.conversationId"></a>
    </div>
</template>

<script>

export default {
    props: ['chat', 'avatars'],
    data() {
        return {
            user: frontendData.user,
            usernameText: '',
            message: '',
            userImage: '',
        }
    },
    created() {
        this.setValues()
    },
    mounted() {
        if (this.chat.lastMessageSenderId === this.user.id) {
            this.$refs.icon.style.display = 'block'
        }
    },
    updated() {
        this.setValues()
    },
    methods: {
        setValues() {
            this.usernameText = this.chat.participants.find(p => p.id !== this.user.id).username
            this.userImage = 'data:image/png;base64,' + this.avatars.find(a => a.id === this.chat.conversationId).img
            this.message = this.chat.lastMessage
        }
    }
}
</script>

<style scoped>

.right-block {
    position: absolute;
    right: 15px;
    top: 50%;
    color: white;
}

.check {
    position: absolute;
    right: 70px;
    display: none;
}

.date {
    position: absolute;
    top: -15px;
    right: 0;
    font-size: 12px;
}

.card {
    position: relative;
    height: 60px;
    width: 100%;
    margin-bottom: 10px;
    background-color: #914ff5;
    border: 2px solid rgba(71, 36, 124, 0.26);
    border-radius: 10px;
}

.card a {
    position: absolute;
    width: 100%;
    height: 100%;
}

.card:hover {
    background-color: #6d3bbb;
}

.card-text {
    position: absolute;
    top: 50%;
    left: 9%;
    margin: 0;
    transform: translateY(-50%);
    display: inline-block;
    text-decoration: none;
    font-size: 18px;
    color: white;
    width: 75%;
    box-sizing: border-box;

    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

.card-user {
    color: #000000;
    font-size: 24px;
    font-weight: bold;
    margin-right: 20px;
}

.card-img {
    position: absolute;
    top: 50%;
    left: 2%;
    margin: 0;
    transform: translateY(-50%);
    display: inline-block;
    width: 40px;
    height: 40px;
    border-radius: 50%;
}

.card-img img {
    width: 100%;
    height: 100%;
    border-radius: 50%;
}

</style>