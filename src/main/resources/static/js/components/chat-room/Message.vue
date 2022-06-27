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

</style>