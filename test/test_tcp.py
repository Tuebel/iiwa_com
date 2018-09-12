#!/usr/bin/env python

import socket


TCP_IP = '172.31.1.147'
TCP_PORT = 30000
BUFFER_SIZE = 1024
MESSAGE = "Hello, World!\n"

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((TCP_IP, TCP_PORT))
print "connected"
s.send(MESSAGE)
print "sent message"
data = s.recv(BUFFER_SIZE)
s.close()

print "received data:", data