<template>

    <div class="notification-block">
        <div class="notification-img">
            <img :src="image" alt="img">
        </div>
        <div class="notification-text">
            <div class="notification-sender" v-html="sender"></div>
            <div class="notification-message">{{ notification.content }}</div>
        </div>
        <a class="notification-link" :href="link"></a>
    </div>
</template>

<script>
export default {
    name: "Notification",
    props: ['notification'],
    data() {
        return {
            link: '',
            sender: '',
            image: '',
        }
    },
    beforeCreate() {
        const url = "/api/notification/get/avatar/" + this.notification.senderId
        console.log(url)
        fetch(url)
            .then(result => result.json())
            .then(data => {
                console.log(this.notification)
                this.image = 'data:image/png;base64,' + data.img
            })
    },
    created() {
        this.link = '/chat/' + this.notification.conversation.conversationId
        if (this.notification.conversation.conversationType === 'NULTIPLE') {
            this.multiple()
        } else {
            this.one_to_one()
        }
    },
    methods: {
        multiple() {
            this.sender = this.notification.conversation.name
            this.sender = this.notification.conversation.name
        },
        one_to_one() {
            const msgSenderId = this.notification.senderId
            const participant = this.notification.conversation.participants.find(p => p.id === msgSenderId)
            this.sender = participant.username
        }
    }
}
</script>

<style scoped>

.notification-block {
    position: relative;
    width: 80%;
    height: 50px;
    background-color: #d4fdc3;
    margin: 0 auto 10px auto;
    padding: 10px;
    border: 1px solid #4ed04e;
    border-radius: 10px;
}

.notification-link {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
}

.notification-img {
    position: absolute;
    width: 30px;
    height: 30px;
}

.notification-img img {
    width: 100%;
    height: 100%;
    border-radius: 50%;
}

.notification-text {
    margin-top: 5px;
    margin-left: 40px;
}

.notification-sender {
    font-weight: bold;
}

.notification-message {
    margin-top: 10px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

</style>