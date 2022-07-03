<template>
    <main class="container">
        <div style="background-color: white; height: 95%; padding: 30px;">
            <h1 style="text-align: center">Settings</h1>
            <settings-info/>
        </div>
    </main>

    <div class="notification-bar">
        <notification v-for="notification in notifications"
                      :key="notification.notificationId"
                      :notification="notification"
                      :notifications="notifications"/>
    </div>

</template>

<script>

import {addHandler} from 'utils/ws'
import SettingsInfo from 'components/settings/SettingsInfo.vue'
import Notification from 'components/Notification.vue'

export default {
    name: "Settings",
    components: {
        SettingsInfo,
        Notification
    },
    data() {
        return {
            notifications: [],
            user: frontendData.user,
        }
    },
    created() {
        addHandler(data => {
            if (data.senderId === this.user.id) {
                return
            }
            this.notifications.push(data)
        })
    },
}

</script>

<style scoped>
</style>