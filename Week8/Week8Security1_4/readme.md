## Exercises
https://docs.google.com/document/d/17qAmASaAAjEAWindIglrdg78LFVitqBklogn8OxjxsQ/edit

### Basic auth

#### Exercise 1

e) Monitor the requests and responses between client and server.
- Explain how your Browser knows when to put up the login window (chrome://net-internals/#events)

Out of a 500 line json file, these are some of the interesting ones:
```json
{"params":{"target":"server","url":"http://localhost:8080/security/admin.html"},"phase":1,"source":{"id":30998,"type":31},"time":"10300860","type":325},  
{"params":{"headers":["Host: localhost:8080","Connection: keep-alive","Cache-Control: max-age=0","Authorization: [46 bytes were stripped]","Upgrade-Insecure-Requests: 1","User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36","Sec-Fetch-Dest: document","Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9","Sec-Fetch-Site: none","Sec-Fetch-Mode: navigate","Sec-Fetch-User: ?1","Accept-Encoding: gzip, deflate, br","Accept-Language: da-DK,da;q=0.9,en-US;q=0.8,en;q=0.7,zh-CN;q=0.6,zh;q=0.5"],"line":"GET /security/admin.html HTTP/1.1\r\n"},"phase":0,"source":{"id":30994,"type":1},"time":"10300860","type":163},  
{"params":{"headers":["HTTP/1.1 200","Cache-Control: private","Expires: Thu, 01 Jan 1970 00:00:00 GMT","Accept-Ranges: bytes","ETag: W/\"449-1584607104906\"","Last-Modified: Thu, 19 Mar 2020 08:38:24 GMT","Content-Type: text/html","Content-Length: 449","Date: Thu, 19 Mar 2020 10:06:27 GMT"]},"phase":0,"source":{"id":30994,"type":1},"time":"10300871","type":168},  
```

- Explain how your server knows that you are logged in for subsequent request

Headers  
https://en.wikipedia.org/wiki/Basic_access_authentication  
- Copy the part that holds the Authorizations part and use the decode option on this link to comment what always must be done as a supplement when using Basic Http (and all other) authentication

```
Encoded:  
Authorization: Basic a3VydF9hZG1pbkBzb21ld2hlcmUuZGs6dGVzdA==  

Decoded:  
kurt_admin@somewhere.dk:test  
```

HTTPS. Hashing.

f) Reflect (write down) the pros & cons of using Basic Authentication, and the use cases where it (still) could be relevant

**Pros:**  
Easy to implement  
"Fast" in terms of:  
- Request has one call for authentication, afterwards set on header.   
- Less code
Relatively safe if used with SSL [link](https://security.stackexchange.com/questions/988/is-basic-auth-secure-if-done-over-https)  


**Cons:**  
Unsafe if used over HTTP.  
HTTPs basic auth is considerably slower  
Lack of encryption is high-risk  
The same unencrypted data is sent every time, so it is really unsafe. MitM attacks can hit at any request, not just the first like in other auth types.  
No consistent way to log out  
Unlike JWT you cannot store any other information than username:password  
Only one GUI to use (browser dependant)  


**When to use:**  
?  

#### Exercise 2

c) Reflect (write down) the pros & cons of using Form-based Authentication, and the use cases where it could be relevant
