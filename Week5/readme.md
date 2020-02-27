# Penetration testing
https://docs.google.com/document/d/1yo5B2Vqh_v_8zx3bIbwZyZnZyaH3erRen1WEinjIbQg/edit#

1) Discover servers
- netdiscover
```
sudo netdiscover -r xxx.xxx.xxx.1/24
//network mask
sudo netdiscover -r 255.255.255.1/24
```
Tip: Run in a second terminal and have it run continuously


2) Scan what could be interesting
- nmap
```
sudo nmap -v -A <ip address>
sudo nmap -v -A 192.168.56.101
```
Or just check all port numbers and write useful results to file:
```
nmap -v -p <portnumber-range> -A <ip> -oN <filename>.txt
nmap -v -p 0-65535 -A 192.168.56.101 -oN meta_ports.txt
```
*(Use -oN <filename.txt> to write to file)*

3) Are there any vulnerabilities that could potentially be attacked?
- nikto
nmap output shows `port 8180` (amongst others)
```
sudo nikto -host <ip address> -p <port>
sudo nikto -host 192.168.56.101 -p 8180
//tomcat server port
```
*(Use > <filename.txt> to write to file)*

4) If allowed, take advantage of any vulnerabilities.
- ?

Nikto output shows tomcat manager is using default password of `tomcat:tomcat`.  
Head to `192.168.56.101:8180/manager/html` and log in