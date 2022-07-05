<template>
    <!--    <form @submit.prevent="createChat" method="post" enctype="multipart/form-data">-->
    <form @submit.prevent="createChat" method="post">

        <input class="enter-name" type="text" placeholder="Enter chat name" v-model="name">

        <div class="load-buttons">
            <div class="pointers load-image" onclick="document.getElementById('imageFile').click()">
                <i class="fa fa-upload fa-xl browse-icon"></i>
                <span style="margin-left: 10px;">load image</span>
            </div>

            <div v-if="enabled" style="display: inline-block">
                <div class="pointers" @click="removeImageFile">
                    <i class="fa-solid fa-xmark fa-xl remove-icon"></i>
                    <span style="margin-left: 10px;">remove image</span>
                </div>
            </div>
        </div>

        <input class="browse-input" type="file" ref="imageFile" id="imageFile" accept="image/*" @change="addImage">
        <input type="button" value="Remove image" @click="removeImageFile" style="display: none">

        <img :src="img" alt="Chat image" id="currImage" class="creation-image">

        <input type="submit" value="Create chat" class="submit-creation" :disabled="name.length === 0" id="submit">
    </form>

    <div class="image-msg image-error" id="imageErr">Error</div>
</template>

<script>
export default {
    name: "CreationForm",
    data() {
        return {
            defaultImg: '',
            img: '',
            enabled: false,
            name: ''
        }
    },
    beforeCreate() {
        fetch("/api/conversation/get/default/image")
            .then(r => r.json())
            .then(data => {
                this.defaultImg = 'data:image/png;base64,' + data.img
                this.img = 'data:image/png;base64,' + data.img
            })
    },
    methods: {
        createChat() {
            const reader = new FileReader()
            reader.readAsDataURL(this.imageFile)
            reader.onload = () => {
                const imgBase64 = reader.result.replace('data:image/png;base64,', '')
                    .replace('data:image/jpeg;base64,', '')
                let data = JSON.stringify({imageFile: imgBase64, name: this.name})
                console.log(imgBase64)

                fetch('/api/conversation/create/new', {
                    method: 'POST',
                    body: data,
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                })
                    .then(r => r.json())
                    .then(data => {
                        this.$router.push('/chat/' + data.conversationId)
                    })
                    .catch(() => {
                        document.getElementById('imageErr').style.display = 'block'
                    })
            };
        },
        async addImage() {
            this.imageFile = this.$refs.imageFile.files[0]
            this.enabled = true
            this.img = URL.createObjectURL(this.imageFile)
        },
        removeImageFile() {
            this.$refs.imageFile.value = null
            this.enabled = false
            this.img = this.defaultImg
        }
    }
}
</script>

<style scoped>

.enter-name {
    display: block;
    margin: auto;
    width: 250px;
    height: 30px;
    font-size: 18px;
    padding: 10px;
    border-color: #6d3bbb;
    border-radius: 10px;
}

input:focus {
    outline: none;
}

.load-buttons {
    margin-top: 20px;
    margin-left: 210px;
    width: auto;
}

.creation-image {
    display: block;
    width: 350px;
    height: 350px;
    margin: auto;
    border-radius: 50%;
}

.submit-creation {
    display: block;
    width: 200px;
    height: 60px;
    font-size: 24px;
    cursor: pointer;
    margin: 20px auto;
}

.submit-creation:disabled {
    cursor: default;
}

</style>