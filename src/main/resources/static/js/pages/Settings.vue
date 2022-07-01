<template>
    <main class="container">
        <div style="background-color: white; min-height: 95%; height: auto; padding: 30px;">
            <settings-image/>
        </div>
    </main>
</template>

<script>

import {addHandler} from 'utils/ws'
import SettingsImage from 'components/settings/SettingsImage.vue'

export default {
    name: "Settings",
    components: {
        SettingsImage
    },
}

</script>

<style scoped>

</style>