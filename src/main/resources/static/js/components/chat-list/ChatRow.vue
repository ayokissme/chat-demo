<template>
    <div class="card">
        <div class="card-content">
            <div class="card-img"></div>
            <div class="card-text">
                <span class="card-user" v-html="usernameText"></span>
                <span class="card-message" v-html="message"></span>
            </div>
        </div>
        <a :href="'/chat/'+ chat.conversationId"></a>
    </div>
</template>

<script>

export default {
    props: ['chat'],
    data() {
        return {
            user: frontendData.user,
        }
    },
    created() {
        this.chat.participants.forEach(u => {
            if (u.id !== this.user.id) {
                this.usernameText = u.username
            }
        })
        this.message = "no content"
    },
}
</script>

<style scoped>

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
    left: 10%;
    margin: 0;
    transform: translateY(-50%);
    display: inline-block;
    text-decoration: none;
    font-size: 18px;
    color: white;

    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
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
    background-color: white;
}

.card-user {
    color: #000000;
    font-weight: bold;
    margin-right: 10px;
}
</style>