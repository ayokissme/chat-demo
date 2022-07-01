<template>
    <div class="answer">
        <div :class="message.senderId === user.id ? 'answer-right' : 'answer-left'">
            <div class="answer-image">
                <img
                    src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/800px-Image_created_with_a_mobile_phone.png"
                    alt="img">
            </div>
            <div class="answer-text">
                <div class="answer-username" v-html="sender"></div>
                <div class="answer-content">{{ message.content }}</div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    props: ['message', 'conversation'],
    data() {
        return {
            user: frontendData.user,
            sender: '',
        }
    },
    created() {
        const sender = this.conversation.participants.find(p => p.id === this.message.senderId)
        this.sender = sender.username
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

</style>