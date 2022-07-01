<template>
    <div class="block">
        <div class="nav-bar">
            <ul>
                <li>
                    <i class="fa-solid fa-comment"></i>
                    <a href="/chat">Chats</a>
                </li>
                <li>
                    <i class="fa fa-comments-o" aria-hidden="true"></i>
                    <a href="/settings">Settings</a>
                </li>
            </ul>
        </div>
        <router-view></router-view>
    </div>
</template>

<script>
export default {
    name: "App"
}
</script>

<style scoped>
@import '../../css/chat.css';

.nav-bar {
    float: left;
    margin-left: 100px;
    margin-top: 80px;
    width: 200px;
    overflow-y: auto;
    height: auto;
}

.nav-bar li {
    list-style-type: none;
    border-radius: 10px;
}

.nav-bar li:hover {
    background-color: #f8f8f8;
}

li a {
    display: block;
    padding: 10px;
    text-decoration: none;
    color: black;
    font-size: 24px;
}

</style>