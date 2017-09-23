var app = require('express')();
var server = require('http').Server(app);
var io = require('socket.io')(server);
var self;

var players = [];

server.listen(9000, function(){
    log("Yerimen Server started.");
});

io.on('connection', function(socket){

    socket.on('playerMoved', function(data){
        socket.broadcast.emit('playerMoved', data);
        updatePlayer(data);
    });

    socket.on('disconnect', function(){
        socket.broadcast.emit('playerDisconnected', { id: socket.id });
        log("Player with ID [" + socket.id + "] just logged out.");
        removePlayer(socket.id);
    });
});

function log(comment){
    console.log("[INFO | " + currentTimestamp() +"] " + comment);
}

function currentTimestamp() {
    var date = new Date();
    return date.toLocaleTimeString()
}

function updatePlayer(data){
    forPlayer(data.id, function(player, index) {
        player.x = data.x;
        player.y = data.y;
    });
}

function forPlayer(id, callback) {
    forEach(players, function(player, index) {
        if(player.id == id){
            callback(player, index);
        }
    });
}

function forEach(list, callback) {
    for(var i = 0; i < list.length; i++){
        callback(list[i], i);
    };
}