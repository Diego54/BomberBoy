var app = require('express')();
var server = require('http').Server(app);
var io = require('socket.io')(server);


var players = 0;
var blocks = [];

server.listen(9000, function(){
    log("BomberLan Server started.");
});

io.on('connection', function(socket){
    players ++
    console.log("Alguien se conecto al server!!!")
    socket.on('playerMoved', function(data){
        socket.broadcast.emit('playerMoved', data);
        updatePlayer(data);
    });

    socket.on('playerDropedBomb', function(data){
        console.log("Jugador Pone bomba!!!")
        socket.broadcast.emit('playerDropedBomb', data);
    });

    socket.on('playersAmount', function(data){
        data.players = players
        log(data.players)
        socket.emit('playersAmountResult', data)
    });

    socket.on('createdBlocks',function (data) {
        blocks = data;

    });

    socket.on('getBlocks',function (data) {
        data.blocks = blocks;
        socket.emit('getBlocksResponse',data)
    });

    socket.on('spawnPowerup',function (data) {
        console.log("EL POWERUP SE ENCUENTRA EN EL TILE: " + data.x+ "@" + data.y + "Y ES DE TIPO: " + data.clazz)
        socket.broadcast.emit('powerupSpawned',data)
    });

    socket.on('disconnect', function(){
        socket.broadcast.emit('playerDisconnected', { id: socket.id });
        players--;
        log("Player with ID [" + socket.id + "] just logged out.");
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