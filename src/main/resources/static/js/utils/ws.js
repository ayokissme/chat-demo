import SockJS from 'sockjs-client'
import { Stomp } from '@stomp/stompjs'

let stompClient = null
let handlers = []

export function connect() {
    stompClient = Stomp.over(function () {
        return new SockJS('/ws')
    });
    stompClient.debug = () => {}
    stompClient.connect({}, frame => {
        console.log(frame)
        stompClient.subscribe('/user/queue/reply', message => {
            handlers.forEach(handler => handler(JSON.parse(message.body)))
        })
        // stompClient.subscribe('/queue', message => {
        //     handlers.forEach(handler => handler(JSON.parse(message.body)))
        // })
    })
}

export function addHandler(handler) {
    handlers.push(handler)
}

export function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect()
    }
    console.log("Disconnected")
}

export function sendMessage(message) {
    stompClient.send("/app/queue/reply", {}, JSON.stringify(message))
}