<template>
    <div class="notification-block">
        <button class="notification-remove" @click="remove">
            <i class="fa-solid fa-xmark fa-xl remove-icon"></i>
        </button>
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
    props: ['notification', 'notifications'],
    data() {
        return {
            link: '',
            sender: '',
            image: '',
        }
    },
    beforeCreate() {
        const url = "/api/notification/get/avatar/" + this.notification.senderId
        fetch(url)
            .then(result => result.json())
            .then(data => {
                this.image = 'data:image/png;base64,' + data.img
            })
    },
    created() {
        this.link = '/chat/' + this.notification.conversation.conversationId
        if (this.notification.conversation.conversationType === 'MULTIPLE') {
            this.multiple()
        } else {
            this.one_to_one()
        }
        setTimeout(() => {
            this.remove()
        }, 5000)
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
        },
        remove() {
            let conversationId = this.notification.conversation.conversationId
            const index = this.notifications.findIndex(c =>
                conversationId === c.conversation.conversationId
            )
            this.notifications.splice(index, 1)
        }
    }
}
</script>

<style scoped>

.notification-remove {
    position: absolute;
    top: 5px;
    right: 5px;
    background: none;
    border: none;
    cursor: pointer;
    border-radius: 25%;
}

.notification-remove:hover {
    background-color: #e1c81f;
}

.notification-block {
    position: relative;
    width: 80%;
    height: 50px;
    background-color: #ffe349;
    margin: 0 auto 10px auto;
    padding: 10px;
    border: 1px solid #e0c004;
    border-radius: 10px;
}

.notification-link {
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 65%;
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