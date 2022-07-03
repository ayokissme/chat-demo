<template>
    <div class="answer">
        <div :class="message.senderId === user.id ? 'answer-right' : 'answer-left'">
            <div class="answer-image">
                <img :src="userImage" alt="img">
            </div>
            <div class="answer-text">
                <div class="answer-username">
                    <span v-html="sender"></span>
                    <span class="createdAt">{{ message.createdAt }}</span>
                </div>
                <div class="answer-content">
                    {{ message.content }}
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    props: ['message', 'conversation', 'avatars'],
    data() {
        return {
            user: frontendData.user,
            sender: '',
            userImage: '',
        }
    },
    created() {
        const sender = this.conversation.participants.find(p => p.id === this.message.senderId)
        this.sender = sender.username

        const avatarOwner = this.avatars.find(p => p.id === this.message.senderId)
        this.userImage = 'data:image/png;base64,' + avatarOwner.img
    },
    mounted() {
        this.$parent.$el.scrollTop = this.$parent.$el.scrollHeight
    }
}
</script>

<style scoped>

.answer {
    margin: 10px 0;
    word-wrap: break-word;
}

.answer::after {
    content: "";
    clear: both;
    display: table;
}

.answer-left {
    min-width: 150px;
    max-width: 500px;
    width: auto;

    padding: 10px;
    float: left;
    background-color: #ffe349;
    border-radius: 15px;
}

.answer-right {
    min-width: 150px;
    max-width: 500px;
    width: auto;

    padding: 10px;
    float: right;
    background-color: #914ff5;
    color: white;
    border-radius: 15px;
}

.answer-username {
    padding-top: 4px;
    font-weight: bold;
    font-size: 18px;
}

.answer-image {
    float: left;
}

.answer-image img {
    width: 30px;
    height: 30px;
    border-radius: 50%;
}

.answer-image::after {
    content: "";
    clear: both;
    display: table;
}

.answer-text {
    margin-left: 40px;
}

.answer-content {
    margin-top: 10px;
}

.createdAt {
    font-size: 12px;
    font-weight: normal;
    margin-left: 10px;
    margin-right: 10px;
}

</style>