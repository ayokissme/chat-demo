<template>
    <form class="load-form" @submit.prevent="saveImage" method="post" enctype="multipart/form-data">
        <div class="pointers load-image" onclick="document.getElementById('imageFile').click()">
            <i class="fa fa-upload fa-xl browse-icon"></i>
            <span style="margin-left: 10px;">load image</span>
        </div>
        <div style="display: none" id="hiddenBlocks">
            <div class="pointers" onclick="document.getElementById('save').click()">
                <i class="fa-solid fa-floppy-disk fa-xl save-icon"></i>
                <span style="margin-left: 10px;">save image</span>
            </div>
            <div class="pointers" onclick="document.getElementById('remove').click()">
                <i class="fa-solid fa-xmark fa-xl remove-icon"></i>
                <span style="margin-left: 10px;">remove image</span>
            </div>
        </div>

        <input class="browse-input" type="file" ref="imageFile" id="imageFile" name="imageFile" accept="image/*"
               @change="addImage">
        <input type="submit" id="save" :disabled="isDisabled" style="display: none">
        <input type="button" id="remove" @click="removeImageFile" :disabled="isDisabled" style="display: none">
    </form>
    <div class="image-msg image-success" id="imageSuc">Success!</div>
    <div class="image-msg image-error" id="imageErr">Error</div>
</template>

<script>
export default {
    name: "SettingsImage",
    props: ['userImage'],
    data() {
        return {
            imageFile: null,
            isDisabled: true,
        }
    },
    methods: {
        addImage() {
            this.isDisabled = false
            this.imageFile = this.$refs.imageFile.files[0]
            document.getElementById('currImage').src = URL.createObjectURL(this.imageFile)
            document.getElementById('hiddenBlocks').style.display = 'inline-block'
        },
        saveImage() {
            const formData = new FormData()
            formData.append('imageFile', this.imageFile)

            fetch('/api/settings/upload/image', {
                method: 'POST',
                body: formData
            })
                .then(() => {
                    document.getElementById('imageSuc').style.display = 'block'
                })
                .catch(() => {
                    document.getElementById('imageErr').style.display = 'block'
                })
        },
        removeImageFile() {
            this.$refs.imageFile.value = null
            this.isDisabled = true
            document.getElementById('currImage').src = this.userImage
            document.getElementById('hiddenBlocks').style.display = 'none'
        }
    }
}

</script>

<style scoped>

.load-form {
    width: 95%;
    margin: auto;
}

.browse-input {
    display: none;
}

.pointers {
    display: inline-block;
    width: 130px;
    cursor: pointer;
    padding: 15px;
    margin-bottom: 20px;
}

.pointers:hover {
    background-color: #cccccc;
}

.image-msg {
    text-align: center;
    font-size: 24px;
    padding-bottom: 20px;
    font-weight: bold;
    display: none;
}

.image-error {
    color: #f13838;
}

.image-success {
    color: #7aff7a;
}

</style>