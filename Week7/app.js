const express = require('express');
const app = express();
const helmet = require('helmet');

app.use(helmet());

app.get('/', function(req, res) {
	res.send('check response headers');
});

app.listen(3001, () => console.log(`app listening on port 3001!`));
