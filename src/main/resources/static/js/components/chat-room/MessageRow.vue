<template>
    <div class="answer">
        <span :class="message.sender.id === user.id ? 'answer-right' : 'answer-left'">
                    {{ message.content }}
        </span>
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

</style>