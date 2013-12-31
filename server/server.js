var data = ['hope is the thing with feathers',
		'that perches in the soul',
		'and sings the tune without the words'];

var pending = []; //workchunk assigned but not finished yet
var todo = []; //workchunck to do

var idleClients = [];
var busyClients = [];

var express = require('express');
var app = express();
app.get('/welcome', function(req, res){
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.writeHead(200, {'Content-Type': 'text/plain'});
  res.end('Hello World\n');
});

app.use(express.bodyParser());

//received client's ID and send the data to be processed
app.post('/register', function(req, res){
  var body = "";
  req.on('data', function (chunk) {
    body +=chunk;
  });
  req.on('end', function () {
    console.log('Client added: ' + body);
  });
  var msg ="";
  if(data.length!=0)
     msg = data.pop();
  console.log('Data to be process: ' + msg);

  res.setHeader('Access-Control-Allow-Origin', '*');
  res.writeHead(200, {'Content-Type': 'text/plain'});
  res.end(msg + '\n');
});

//receive the client's result
app.post('/result', function(req, res){
  var body = "";
  req.on('data', function (chunk) {
    body +=chunk;
  });
  req.on('end', function () {
    console.log('Results received: ' + body);
  });

  res.setHeader('Access-Control-Allow-Origin', '*');
  res.writeHead(200, {'Content-Type': 'text/plain'});
  res.end('Results received\n');

});

app.listen(1337);
console.log('Listening on port 1337');