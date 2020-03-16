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

-----------------  

# Class notes (Friday)


Name som application layer protocols:  

HTTP, Telnet, FTP, DNS  

Network protocols:  

IPv4, IPv6, ICMP

How does application layer packets get from A to B?  
TCP/UDP with the help of ARP?  

Purpose of the transport layer?  

Conceptual differences between TCP and UDP?  
TCP holds the connection therefore longer overhead but "guaranteed" arrival. Receipts are sent. Used for many connections, e.g. file transfers.  
UDP sends the packets and does not care. Often used for streaming. Much faster.  

What is DNS?  
Domain Name System. Local or external caches resolves <websitename> to their IP. vulnerable to spoof attack.

What is DHCP, what happens on startup?  
Protocol:  
Server: assigns a IP address to devices that request it. devices start as 0.0.0.0 and are then assigned an actual IP.












