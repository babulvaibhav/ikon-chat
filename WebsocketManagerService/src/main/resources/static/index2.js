var stompClient = null;

function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('response').innerHTML = '';
}

function connect() {
    let userName = document.getElementById('from').value;
//    var socket = new SockJS('/ikonchatws');
//    stompClient = Stomp.over(socket);
    stompClient = new StompJs.Client({
      brokerURL: 'ws://localhost:8081/ikonchatws'
    });
//    stompClient.connect({}, function(frame) {
//        setConnected(true);
//        console.log('Connected: ' + frame);
//        console.log(userName)

        // Subscribe to broadcast messages
        /*stompClient.subscribe('/topic/broadcast', function(messageOutput) {
            showMessageOutput('Broadcast', JSON.parse(messageOutput.body));
        });*/

//        stompClient.subscribe('topic/group/groupId', function(messageOutput) {
//            showMessageOutput('Group', JSON.parse(messageOutput.body));
//        });
//
//        // Subscribe to private messages
//        stompClient.subscribe('/user/${userName}/queue/messages', function(messageOutput) {
//            showMessageOutput('Private', JSON.parse(messageOutput.body));
//        });

//    });
    stompClient.onConnect = (frame) => {
        setConnected(true);
        console.log('Connected: ' + frame);
        setTimeout(() => {
            stompClient.subscribe('topic/group', function(messageOutput) {
                showMessageOutput('Group', JSON.parse(messageOutput.body));
            });

            // Subscribe to private messages
            stompClient.subscribe('/user/${userName}/queue/messages', function(messageOutput) {
                showMessageOutput('Private', JSON.parse(messageOutput.body));
            });
        }, 1.5*1000)
    };
    stompClient.activate();
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log('Disconnected');
}

function sendMessage(destination) {
    var from = document.getElementById('from').value;
    var text = document.getElementById('text').value;
//    stompClient.send(destination, {}, JSON.stringify({ 'from': from, 'text': text }));
    stompClient.publish({
        destination: destination,
        body: JSON.stringify({'from': from, 'text': text})
    });
}

function showMessageOutput(type, messageOutput) {
    var response = document.getElementById('response');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(type + ' - ' + messageOutput.from + ': ' + messageOutput.text + ' (' + messageOutput.time + ')'));
    response.appendChild(p);
}