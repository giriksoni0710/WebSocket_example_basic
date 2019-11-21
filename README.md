# WebSocket_example_basic


What is web socket and why use it?

The idea of websocket was because of the limitation of http request. In http, the client requires a resource and the server provides it.
Every time the client has to request data for a response which makes it strictly unidirectional. 

WebSockets allow both the server and the client to exchange information at any time without any relation to a previous request.
Websocket is a computer communication protocol which follows the handshake process which ensures that server is always 
connected to the client. It is a two way communicationn protocol which ensures that client and server are always synced together.

Websocket is more advantageous over http when it comes to real time data. It does not require the http request/response overhead to be sent 
everytime with each message. This makes it more faster and efficient than http. A client has to make request everytime to request data with http,
whereas the websocket server makes connection request just once and listens to the messages being sent. They are beneficial when a client wants regular
updates about the resource.

eg. If you would like to access the soccer updates from last week, it would be better to do it with http as the data is relatively constant.
But when you are watching a live game with updates every minute or so, the server and client have to be in constant connection with each 
other to receive the timely updates. Even chatting is a great example for web sockets.

The example demonstrates the use of websockets to have a real time communication. okhttp library is used to make the first request to the server.

This is a demo test server that websocket.org provides.

https://www.websocket.org/echo.html






