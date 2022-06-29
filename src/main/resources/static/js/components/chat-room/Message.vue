<template>
    <div class="answer" :class="message.sender.id === user.id ? 'answer-right' : 'answer-left'">
        {{ message.content }}
    </div>
</template>

<script>
export default {
    props: ['message'],
    data() {
        return {
            user: frontendData.user,
        }
    },
    created() {
        // console.log(JSON.parse(this.user))
    }
}
</script>

<style scoped>

.answer {
    margin: 10px 0;
    width: 500px;
}

.answer::after {
    content: "";
    clear: both;
    display: table;
}

.answer-left {
    padding: 10px;
    float: left;
    background-color: #ffe349;
    border-radius: 15px;
}

.answer-right {
    padding: 10px;
    float: right;
    background-color: #914ff5;
    color: white;
    border-radius: 15px;
}

</style>