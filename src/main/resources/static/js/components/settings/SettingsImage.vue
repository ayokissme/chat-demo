<template>
    <h1 style="text-align: center">Settings</h1>
<!--    <form @submit.prevent="saveImage" name="message" enctype="multipart/form-data">-->
    <form @submit.prevent="saveImage" method="post" enctype="multipart/form-data">
        <input type="file" ref="imageFile" name="imageFile" accept="image/*" @change="addImage">
        <input type="submit" :disabled="isDisabled" value="upload">
    </form>
    <input type="submit" @click="removeImageFile" :disabled="isDisabled" value="remove">
    <div id="preview">
        <img v-if="url" :src="url" alt="img"/>
    </div>
    <img src="" id="itemPreview" alt="emm">
</template>

<script>
export default {
    name: "SettingsImage",
    data() {
        return {
            imageFile: null,
            isDisabled: true,
            url: null,
        }
    },
    methods: {
        addImage(e) {
            this.isDisabled = false
            this.imageFile = this.$refs.imageFile.files[0];
            this.url = URL.createObjectURL(this.imageFile);
        },
        saveImage() {
            const formData = new FormData()
            formData.append('imageFile', this.imageFile)

            fetch('/api/settings/upload/image', {
                method: 'POST',
                body: formData
            })
                .then(r => r.json())
                .then(data => {
                    const blob = new Blob(data.imageFile, { type: "image/jpeg" });
                    const imageUrl = URL.createObjectURL(blob);
                    console.log(imageUrl)
                    document.getElementById("itemPreview").src = imageUrl;
                })
        },
        removeImageFile() {
            this.$refs.imageFile.value = null
            this.isDisabled = true
            this.url = null
        }
    }
}

</script>

<style scoped>

</style>