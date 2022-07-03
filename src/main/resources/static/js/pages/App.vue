<template>
    <div class="block">
        <div class="nav-bar">
            <ul>
                <li>
                    <a href="/chat">
                        <i class="fa-solid fa-comment fa-sm"></i>
                        <span>Chats</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="fa-solid fa-plus fa-sm"></i>
                        <span>New chat</span>
                    </a>
                </li>
                <li>
                    <a href="/settings">
                        <i class="fa-solid fa-gear fa-sm"></i>
                        <span>Settings</span>
                    </a>
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
    margin-left: 80px;
    margin-top: 80px;
    width: auto;
    overflow-y: auto;
    height: auto;
}

.nav-bar ul {
    float: left;
}

.nav-bar li {
    margin: 20px 0;
    padding: 10px 50px 10px 10px;
    text-align: left;
    list-style-type: none;
    border-radius: 10px;
}

.nav-bar li:hover {
    background-color: #f8f8f8;
}

.nav-bar a {
    text-decoration: none;
    color: black;
    font-size: 24px;
}

.nav-bar span {
    margin-left: 10px;
}

</style>