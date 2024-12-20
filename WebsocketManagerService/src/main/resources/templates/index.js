//const stompClient = new StompJs.Client({
//    brokerURL: 'ws://localhost:8081/ikonchatws'
//});

const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8081/ikonchatws',
    connectHeaders: {
            'user-id': "550e8400-e29b-41d4-a716-446655440000" // Pass the user ID dynamically
    }
});

stompClient.onConnect = (frame) => {
    let userName = document.getElementById('from').value;
    let receiverName = document.getElementById('receiver').value;
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/greetings', (greeting) => {
        showGreeting(JSON.parse(greeting.body).content);
    });

    stompClient.subscribe('/topic/group/mnop070d-5c4k-7f0d-4d8b-162843c14896', (messageOutput) => {
        showMessageOutput('Group', JSON.parse(messageOutput.body));
    });

//    stompClient.subscribe(`/user/${userName}/queue/messages`, function(messageOutput) {
//                    showMessageOutput('Private', JSON.parse(messageOutput.body));
//    });

    stompClient.subscribe(`/queue/messages/${userName}`, function(messageOutput) {
                        showMessageOutput('Private', JSON.parse(messageOutput.body));
        });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function connect() {
    stompClient.activate();
}

function disconnect() {
    if (stompClient != null) {
        stompClient.deactivate();
    }
    setConnected(false);
    console.log('Disconnected');
}

function sendMessage(destination) {
    var from = document.getElementById('from').value;
    let receiverName = document.getElementById('receiver').value;
    var text = document.getElementById('text').value;
    stompClient.publish({
        destination: destination,
        body: JSON.stringify({
            "senderId" : from,
            "receiverId" : receiverName,
            "messageSentAt" : new Date(),
            "messageContent" : {"text" : text}
        })
    });
}

function sendGroupMessage(destination) {
    var from = document.getElementById('from').value;
    var message = document.getElementById('text').value;
    stompClient.publish({
        destination: destination,
        body: JSON.stringify({
            "senderId" : from,
            "groupId" : "mnop070d-5c4k-7f0d-4d8b-162843c14896",
            "messageSentAt" : new Date(),
            "messageContent" : {"text" : message}
        })
    });
}

function showMessageOutput(type, messageOutput) {
    var response = document.getElementById('response');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(type + ' - ' + messageOutput.senderId + ': ' + messageOutput.messageContent["text"] + ' (' + messageOutput.messageSentAt + ')'));
    response.appendChild(p);
}

function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('response').innerHTML = '';
}