# DM557-Network-and-Security
a simple webserver capable of processing only one request.

- create a connection socket when contacted by client browser.
- receive the HTTP request from this connection.
- parse the request to determine the specific file being requested.
- get the requested file from the servers filesystem.
- create an HTTP response message consisting of the requested file preceded by header lines.
- send the response over the TCP connection to the requesting browser. If not found -> return 404 error.

