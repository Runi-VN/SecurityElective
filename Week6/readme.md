# Class notes (Thursday)

Overview: https://github.com/securitydatspring2019/Week-06-Networks-and-Security  
**See slides**  

TCP/IP stack layers are generally independent in that they work by themselves and then forward their data to the next layer.

## Application layer  
Data  
Protocols used here are what we are familiar with: HTTP, DNS, FTP, POP, Telnet.  

## Transport Layer  (Slides are good on TCP vs UDP!)
Addresing: Port numbers 
TCP/UDP (& others)  
Splits data up  
Sends receipts back and forth  
TCP works in duplex (back/forth) and keeps the connection while necessary, while UDP is connectionless, unreliable  
TCP is good for content that needs to be 100% correct, such as files  
UDP is good for other content, such as streaming (sound, video, other) - which is why you may experience interrupts, artifacts, etc.  
UDP has reduced overhead compared to TCP  

## Internet Layer  
"The internet", ip addresses  
Transports data between networks (routers all over the world)  
No guarantee that packets are sent the same (router)way - Available routers may change ("hops")

	
## Network Layer
Physical network, mac addresses  
Transports data between the local network (local gateways (switches, hubs, computers, etc))

-----------------  


#### DHCP - Dynamic Host Configuration Protocol  
Hosts can ask for a IP to be given by the DHCP server for a dynamic IP opposite of a static IP  

#### DNS - Domain Name System  
resolving/caching - Ask own cache first, ask ISP cache, recursive DNS search