let stompClient = null;

const ws_addr = "/chat-ws"
const rest_addr = "http://localhost:8080"
const chats_addr = "/api/chats-management"

$(document).ready(function () {
    // console.log("Index page is ready");
    connect().then(() => console.log(
        "finished fetching"
    ))

    $("#send").click(function () {
        sendMessage(1);
    });

});

async function connect() {
    // Connect to ws
    console.log("Connecting to websocket")
    let socket = new SockJS(ws_addr);
    stompClient = Stomp.over(socket);
    // stompClient.debug = null

    // Get chat info
    fetchAvailableChats().then((res) => {
        let chatIds = res.map((chat) => chat.id)

        // Fill chat messages
        for (const id of chatIds) {
            fetchDataForChat(id).then((ch_msg) => {
                ch_msg.content.forEach((msg => {
                    showMessage(msg.content)
                }))
            }).catch(() => {
                console.log("Cannon fetch messages")
            })
        }

        // Set listeners
        stompClient.connect({}, function (frame) {
            console.log(frame);
            chatIds.forEach((id) => {
                console.log(`Subscribing to /chat-queue/${id}`)
                stompClient.subscribe(`/chat-queue/${id}`, function (message) {
                    showMessage(JSON.parse(message.body).content);
                });
            })
            // stompClient.subscribe(`/chat-queue/1`, function (message) {
            //     showMessage(JSON.parse(message.body).content);
            // });
        });
    }).catch(() => {
        console.log("Cannot fetch chats")
    })
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

function sendMessage(id) {
    // console.log("sending message");
    // stompClient.send("/ws/message", {}, JSON.stringify({'messageContent': $("#message").val()}));
    fetch(rest_addr + chats_addr + `/${id}/messages`, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(
            {
                "fromId": 1,
                "content": $("#message").val()
            }
        )
    })
        .then(response => response.json())
        .then(response => console.log(JSON.stringify(response)))
 }

async function fetchAvailableChats() {
    const response = await fetch(`${rest_addr}${chats_addr}`)
    const data = await response.json()
    console.log("Chats fetched")
    console.log(data)
    return data
}

async function fetchDataForChat(id) {
    const response = await fetch(`${rest_addr}${chats_addr}/${id}?page=0`)
    const data = await response.json()
    console.log("Chat msg fetched")
    console.log(data)
    return data
}