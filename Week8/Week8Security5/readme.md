## Continued exercises
https://docs.google.com/document/d/17qAmASaAAjEAWindIglrdg78LFVitqBklogn8OxjxsQ/edit

From `Implementing our own JWT-based Authentication Strategy for a REST-API.`

g) Copy the token from the Postman request into the clipboard and paste it into this page:  
https://jwt.io/ 
Verify that we can read the content and answer the following questions:  

Token:  
```
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwZXRlcl91c2VyQHNvbWV3aGVyZS5kayIsInJvbGVzIjoidXNlciIsImlzcyI6Im1lIiwiZXhwIjoxNTg0NjM5NzAyLCJpYXQiOjE1ODQ2Mzc5MDIsInVzZXJuYW1lIjoicGV0ZXJfdXNlckBzb21ld2hlcmUuZGsifQ.Xb16GtwvRp2aOxsW9xHchEOQFLdaDPlc0wl9RKk4MFk

```  

Decoded:
```json

HEADER:
{
  "alg": "HS256"
}
PAYLOAD:
{
  "sub": "peter_user@somewhere.dk",
  "roles": "user",
  "iss": "me",
  "exp": 1584639702,
  "iat": 1584637902,
  "username": "peter_user@somewhere.dk"
}
```

What is the advantages of having a Token with the provided information?  
- On the client?  


- On the server?  
Handles `roles` & `expiry time`  

Why is it not possible for hackers to create a similar Token, and use with our system?  
The signature  

How should Tokens “always” be transported?  
With Transport Layer Security (TLS) -- SSL/HTTPS.  


##### Final reflections.

Answer the following questions:

- What is the advantage (if any) for a REST-based API of using JWT’s compared to session Cookies  
No DB and no DB lookup, token validation happens as the requests come in.  
Simple to use, scalable.  
Can be used across services that all access the public key to verify the token.  
Immune to XSRF (https://stackoverflow.com/a/35059874) as a standard. Cookies can implement too.  
Cookies are sent out for every single request. Tokens only when requested.**???**  


- What is the advantage (if any) for a REST based API of using JWT’s compared to Basic HTTP Authentication  
More secure. (Not as prone to MITM as Basic Auth)  
You can actually log out.
etc etc  


- What is the disadvantage (if any) with the implemented JWT-solution  
Token is larger than cookies. 8KB vs 4KB  
One key secret can be risky. It holds the single access to all tokens.  
It is easier to revoke access from sessions compared to JWTs, where you'd implement blacklisting or store JWTs...  
JWT storage isn't as good as cookie storage:  
**LocalStorage**: The token persists even after closing browser window.  
*SessionStorage**: The token is discarded after closing browser window. Token is not available in other tabs than the main one.  

Maybe store JWT in cookies?  
Token is discarded on close  
Using a session cookie will allow you to authenticate accross tabs  
However, cookies are sent on every request. If the cookie isn't HTTPS-only, you are in for a bad time.  



- What will a client (Single Page WEB, Mobile App, etc.) have to do in order to use this API  
1. Get a user registered  
2. Login  
3. Use the API for the next 30 minutes.  
