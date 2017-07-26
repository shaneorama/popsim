var socket = null;

function connect() {
    socket = new SockJS('/popsim');
    socket.onopen = function(){onConnect();}
    socket.onmessage = function(msg){receive(msg.data);}
    socket.onclose = function(){disconnect();}
}

function onConnect() {
    console.log('Connected');
    foo = $('#main').append('Connected');
}

function disconnect() {
    console.log('Disconnected');
    foo = $('#main').append('Disconnected');
}

function receive(msg) {
    $('#main').append(msg);
    msg.split
    switch()
}

function sendMsg(msg) {
    socket.send(msg);
}


$(function () {
    connect();
});

