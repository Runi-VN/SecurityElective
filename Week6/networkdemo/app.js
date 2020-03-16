// const express = require('express');
// const app = express();
// app.use(express.static('public'));
// const PORT = 5551;
// app.listen(PORT, () => console.log(`Server started: ${PORT}`));

const express = require('express');
const app = express();
app.use(express.static('public'));
const PORT = 5551;

const dgram = require('dgram');
const UDP_PORT = 5555;
var socket = dgram.createSocket('udp4', function(msg, rinfo) {
	var daytime = 'You want our current time, OK here it is: ' + new Date().toISOString();
	let result = 0;
	//To simulate a service that takes longer time
	for (let i = 0; i < 10000000; i++) {
		result = i;
	}
	try {
		socket.send(Buffer.from(daytime), rinfo.port, rinfo.address);
	} catch (err) {
		console.log(err);
	}
});
socket.on('listening', () => {
	let addr = socket.address();
	console.log(`Listening for UDP packets at ${addr.address}:${addr.port}`);
});
socket.bind(UDP_PORT);

app.get('/', (req, res) => {
	res.send(`UDP-time service is running on port 5555 -
  test from Kali, with: netcat -u ${req.hostname} 5555  `);
});

app.listen(PORT, () => console.log(`HTTP-Server started listening on port ${PORT}`));
