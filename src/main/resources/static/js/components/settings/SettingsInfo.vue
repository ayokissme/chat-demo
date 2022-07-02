<template>
    <div class="info-card">
        <div class="user-image">
            <img src="" alt="your image" id="currImage">
        </div>
        <div class="username">
            {{user.username}}
        </div>
        <settings-image :userImage="userImage"/>
    </div>
</template>

<script>
import SettingsImage from 'components/settings/SettingsImage.vue'

export default {
    name: "SettingsInfo",
    components: {
        SettingsImage,
    },
    data() {
        return {
            user: frontendData.user,
            userImage: null,
        }
    },
    created() {
        fetch('/api/settings/get/image', {
            method: 'get',
        })
            .then(r => r.json())
            .then(data => {
                let image = document.getElementById('currImage')
                this.userImage = 'data:image/png;base64,' + data.img
                image.src = this.userImage
            })
            .catch(e => console.log(e))
    },
}
</script>

<style scoped>
.info-card {
    width: 530px;
    margin: auto;
    height: auto;
    background-color: #6d3bbb;
    border-radius: 10px;
}

.user-image {
    position: relative;
    padding: 20px 0;
    width: 300px;
    height: 300px;
    margin: auto;
}

.user-image img {
    width: 100%;
    height: 100%;
    border-radius: 50%;
}

.username {
    text-align: center;
    color: white;
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 20px;
}
</style>