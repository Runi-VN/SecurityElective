const express = require('express');
const app = express();
app.use(express.static('public'));
const PORT = 5551;
app.listen(PORT, () => console.log(`Server started: ${PORT}`));
