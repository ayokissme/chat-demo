import SockJS from 'sockjs-client'
import { Stomp } from '@stomp/stompjs'

var stompClient = null

export function connect() {
    stompClient = Stomp.over(function () {
        return new SockJS('/gs-guide-websocket')
    });
    stompClient.debug = () => {}
    stompClient.connect({}, frame => {
        stompClient.subscribe('/topic/activity', message => {
            handlers.forEach(handler => handler(JSON.parse(message.body)))
        })
    })
}

export function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect()
    }
    console.log("Disconnected")
}

export function sendMessage(message) {
    stompClient.send("/app/change-message", {}, JSON.stringify(message))
}