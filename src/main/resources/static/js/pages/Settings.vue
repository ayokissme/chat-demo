<template>
    <main class="container">
        <div style="background-color: white; height: 95%; padding: 30px;">
            <h1 style="text-align: center">Settings</h1>
            <settings-info/>
        </div>
    </main>
</template>

<script>

import {addHandler} from 'utils/ws'
import SettingsInfo from 'components/settings/SettingsInfo.vue'

export default {
    name: "Settings",
    components: {
        SettingsInfo
    },
}

</script>

<style scoped>

</style>