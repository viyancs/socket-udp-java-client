socket-udp-java-client
============

this repository is communication using protocol udp with the server using nodejs and the client using java
<br>
requiretment : 
<br>
1) node.js <br>
2) java socket UDP using DatagramPacket and DatagramSocket<br>
3) json 



Running The Application
-----------------------

* install nodejs <br>
* install java SDK,JRE or <br>
* install IDE(recomended Netbeans because the repository using netbeans) <br>
	 
* run the server that been use nodejs<br>

* for the server code 
*  ```var dgram = require("dgram");
    var server = dgram.createSocket("udp4");
    server.on("message", function (msg, rinfo) {
        var data = JSON.parse(msg);
        console.log("server got: " + data.balance + " from " + rinfo.address + ":" + rinfo.port);
        var data = {"name" : "test","args" : {"data":[{"a":1}]}};
        //send data to client
        var message = new Buffer(JSON.stringify(data));//JSON.stringify(data)
        server.send(message, 0, message.length, rinfo.port, rinfo.address, function(err, bytes) {
            console.log("error mamen = " + err );
            console.log("data bytes = " + bytes);
            //server.close();
        });
    });
    server.on("listening", function () {
    var address = server.address();
    console.log("server listening " +
        address.address + ":" + address.port);
    });
    server.bind(41234);`

save to app.js run with `sudo node app.js`
* run the UDPImplementation.java to test application<br>
* ```java
        public static void main(String[] args) throws UnknownHostException, SocketException, IOException {
        InetAddress IPAddress =  InetAddress.getByName("localhost");
        byte[] sendData = new byte[1024];

        JSONObject obj = new JSONObject();
        obj.put("name", "viyancs");
        obj.put("num", new Integer(100));
        obj.put("balance", new Double(1000.21));
        obj.put("is_vip", new Boolean(true));
        obj.put("nickname",null);

        sendData = obj.toJSONString().getBytes();
        SocketUDP client = new SocketUDP(IPAddress,41234,callback);
        client.connect();
        client.send(sendData);
        client.initReceive();
        client.close();
        client.disconnect();
    }
    
    final static public UDPCallback callback = new UDPCallback() {

        @Override
        public void onConnect() {
            System.out.println("server is connected");
        }
        
        @Override
        public void onMessage(String event, JSONArray json) {
            System.out.println("the event is " + event + "\n");
            System.out.println("the data is " + json.toJSONString() + "\n");
        }

        @Override
        public void onMessage(String data) {
            System.out.println("data from server : " + data);
        }
        
        @Override
        public void onMessage(JSONObject msg) {
            System.out.println("data from server = " + msg.toJSONString());           
        }
        @Override
        public void onMessage(String event, JSONObject json) {
            System.out.println("data from server = " + json.toJSONString());  
        }
    

        @Override
        public void onClose() {
            System.out.println("client is close the request");
        }

        @Override
        public void onError(ParseException ex) {
            System.out.println("something wrong : " + ex.getMessage());
        }

        @Override
        public void onDisconnect() {
            System.out.println("client is disconnect");
        }
    };

Features
-----------------------

* send data using string format 
* send data using JSONFormat (JSONObject,JSONArray) and dynamical JSON.
* receive data using string format
* receive data using json format

Bugging 
-----------------------

* for now i don't detected bug but i can help a lot if someone can found the bug.

Licence 
----------------------
if you want to use this repository please  don't remove comment in each code, fork and follow this repository if any question send email to msofyancs@gmail.com , i wanna to update this code to be better.

	

