# Week 7 Exercise
https://docs.google.com/document/d/102KrU8kMEeNwAqWJqFjqwjkN0BxtRTugyIqDbt2-zA0/edit

## Exercise 1, A6 defautl settings
Using this link: http://sec-dat-demo.surge.sh/  

### Exercise a  
 See whether you can discover the following properties of the application (not all are necessary security-problems).  
 Use the GUI provided by the application (as a start), Postman, nmap and obviously your browser's Developer Tools, when probing the app:

Nmap scanned frontend: `nmap -v -A 138.197.235.123`  
```
Discovered open port 443/tcp on 138.197.235.123
Discovered open port 53/tcp on 138.197.235.123
Discovered open port 80/tcp on 138.197.235.123
Discovered open port 22/tcp on 138.197.235.123
Discovered open port 9002/tcp on 138.197.235.123
Discovered open port 9001/tcp on 138.197.235.123
Discovered open port 9003/tcp on 138.197.235.123
```
 

- OS  
Linux/Ubuntu

- Server Architecture (Come up with a “guess” and provide arguments for your suggestion)
Tomcat  
Nginx

- Server(s)   
Linux, Nginx, Tomcat

- Programming Language  

**Frontend**:  ReactJS  
**Backend**: Java

React Tools respond to the website. Developer tools show: CSS, JS, webpack  
Make bad login request to backend => Network => Response. Full stacktrace.  

- Important packages, classes used by the Programming Language
Jax-RS through glassfish/jersey  

- Can you see “what kind of pages” logged-in users will see, without having a way to log in?  
Looking through `static/js` shows a lot of information.  


- Can you discover the client technologies used
`node_modules` show REACT amongst others.  

- Default users and Passwords = the ability to login 
default user:  
user | test  
admin | test  

- If you can make a successful login, can you: discover the algorithm used to “protect” the token, the lifetime of the token, the role, assigned to you by the system?
[Direct link](https://jwt.io/#debugger-io?token=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjoiYWRtaW4iLCJleHAiOjE1ODQwMDMzNjMsImlhdCI6MTU4NDAwMTU2MywiaXNzdWVyIjoic2VtZXN0ZXJkZW1vX3NlY3VyaXR5X2NvdXJzZSIsInVzZXJuYW1lIjoiYWRtaW4ifQ.UL5HQmvO_f5s8iMc7Wej_AJkyF87qR_w1OTQzOuCPsw)  
```json
HEADER:
{
  "alg": "HS256",
  "typ": "JWT"
}
PAYLOAD:
{
  "sub": "admin",
  "roles": "admin",
  "exp": 1584003363, //30 minutes
  "iat": 1584001563,
  "issuer": "semesterdemo_security_course",
  "username": "admin"
}
```
- How/where is the token stored by the client  
jwtToken in localStorage  

- Can you determine/guess(must be qualified) whether front-end, REST back-end and Database is running on the same or on different servers?  
Frontend is hosted on Surge => 138.197.235.123  
Backend is hosted on dat-security.dk at endpoint `secDemoA6/api` => 46.101.227.238  
Database is hosted on backend server (MySQL)  

- You are hereby granted permission to scan the server hosting the BACKEND

 Using `nmap -v -A 46.101.227.238` (backend)  
 MySQL  
 nginx
 
 Open ports:  
 22/tcp (ssh)
 80/tcp (http)
 443/tcp (https)
 3306/tcp (mysql)
 
 Tomcat 9.0.22 (main website -> `wwww.dat-security.dk/manager`) 

- Can you determine which database is used by the backend?
MySQL V. 8.0.19  

- Have you discovered any unnecessary features which are enabled or installed (e.g. unnecessary ports, services, pages, accounts, or privileges)

- Who owns the domain used for the server?

- Is the server hosted “privately”, by a cloud provider, or …..?  
whois or nslookup:  
https://www.dk-hostmaster.dk/find-domaenenavn --> dat-security.dk  
Owned by the teacher.  

```
$ nslookup dat-security.dk
Non-authoritative answer:
Server:  dns.google
Address:  8.8.8.8

Name:    dat-security.dk
Address:  46.101.227.238
```

- Can you detect/discover more properties of the application than those suggested above?
- Open developer tools, and the network-tab. Enter this URL (exactly as given) http://studypoints.info  
Explain the first two requests, you monitor. Is this a problem, could this have been done better” (this probably require that you have read the suggested readings related to security-headers)  

Accessing http immediately redirects us to **https**.  
The **first request** towards `http:...` is marked *301 Moved Permanently* which redirects us to location `https://studypoints.info`  
It is marked with nginx/1.14.0 - nginx reverse proxy + certbot probably  

The browser then re-tries, requests the new location and we are sent to the https site.  


### Exercise b  
List all the things “done wrong” (misconfigured) in this application

## Exercise 2 - Security Headers  
a) Enter this link http://security-headers.dat-security.dk/ in a browser, and explain shortly the purpose of all response headers, related to security.  

**301 - moved permanently**, redirects to https thx to nginx. The next request redirect response headers: 
 
```
Connection: keep-alive
Content-Encoding: gzip
Content-Security-Policy: default-src 'self'; style-src 'self' maxcdn.bootstrapcdn.com; font-src 'self'  https: data:
Content-Type: text/html; charset=utf-8
Date: Thu, 12 Mar 2020 10:20:56 GMT
ETag: W/"1a-3TTwMsDVaEQF0vLxXluE0a8W1QA"
Server: nginx/1.14.0 (Ubuntu)
Strict-Transport-Security: max-age=15552000; includeSubDomains
Transfer-Encoding: chunked
X-Content-Type-Options: nosniff //Does not allow the browser to perform MIME-type sniffing, guessing what MIME-type is used
X-DNS-Prefetch-Control: off
X-Download-Options: noopen
X-Frame-Options: SAMEORIGIN //only allow xframes from same origin (duh) as server
X-XSS-Protection: 1; mode=block
```

https://owasp.org/www-project-secure-headers/#tab=Headers  
After revisiting: **307 - internal redirect** w/ header `Non-Authoritative-Reason: HSTS`  
todo rewrite

**Visiting https://security-headers.dat-security.dk/api/json** shows something that looks like *json* but the response header is actually `Content-Type: text/html; charset=utf-8`  

Whereas **Visiting https://security-headers.dat-security.dk/api/jsonwithcontenttype** shows actual JSON & header: `Content-Type: application/json; charset=utf-8`  


b) Setup a simple web server (Tomcat, Nginx, Express ….) which should set most of the security headers for all requests.  

see project in this folder. todo: link  

Resulting headers:  
```
HTTP/1.1 200 OK
X-DNS-Prefetch-Control: off
X-Frame-Options: SAMEORIGIN
Strict-Transport-Security: max-age=15552000; includeSubDomains
X-Download-Options: noopen
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Content-Type: text/html; charset=utf-8
Content-Length: 22
ETag: W/"16-a3gFH1MFUzcF1/pfnxHRZaR9hwM"
Date: Thu, 12 Mar 2020 11:40:34 GMT
Connection: keep-alive
```

## Exercise 3 - A9 components with known vulnerabilities  
How can we (you) ensure that our maven dependencies do not contain Known Vulnerabilities?  

a) Google this topic an see what kind of tools you can find, the following are suggestions:  
https://snyk.io/vuln/?packageManager=all  
https://www.owasp.org/index.php/OWASP_Dependency_Check  

b) Use one or more of the tools/strategies found above and use them to check some of your previous Java/Maven projects (for example the backend seed from your 3. Semester CA3)  

c) If you are following Python or JavaScript come up with a similar strategy for Python/JavaScript dependencies  
