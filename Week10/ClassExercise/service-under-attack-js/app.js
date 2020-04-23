const express = require('express');
const app = express();
const port = 3000;

app.get('/hello', (req, res) => {
	console.log('request received on attack-me');
	res.send('Hello World!');
});
app.post('/attack-me', (req, res) => {
	console.log('request received on attack-me');
	res.send(`${req.body}\nBody received!`);
});

app.listen(port, () => console.log(`Example app listening at http://localhost:${port}`));
