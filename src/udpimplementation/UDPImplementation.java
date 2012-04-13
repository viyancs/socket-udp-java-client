/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package udpimplementation;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import vyn.udp.SocketUDP;
import vyn.udp.UDPCallback;

/**
* UDP Implementation - socket-udp-java-client
*
* process send data to server
* parsing data from server using callback
*
* @author M Sofiyan
* @email msofyancs@gmail.com
* @skypeid viyancs
* if you want to using part of full this code, please don't remove this comment
*
**/
public class UDPImplementation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException {
        InetAddress IPAddress =  InetAddress.getByName("localhost");
        byte[] sendData = new byte[1024];

        JSONObject obj = new JSONObject();
        obj.put("name", "viyancs");
        obj.put("num", new Integer(100));
        obj.put("balance", new Double(1000.21));
        obj.put("is_vip", new Boolean(true));
        obj.put("nickname",null);
        String data = "testing send data";
        sendData = data.getBytes();

        //sendData = obj.toJSONString().getBytes();
        SocketUDP client = new SocketUDP(IPAddress,41234,callback);
        client.connect();
        client.send(sendData);
        client.initReceive();
        client.close();
    }
    /**
     * callback for request and response 
     */
    final static public UDPCallback callback = new UDPCallback() {
        
        // when server is connected
        @Override
        public void onConnect() {
            System.out.println("server is connected");
        }
        //when onmessage with string and jsonArray data
        @Override
        public void onMessage(String event, JSONArray json) {
            System.out.println("the event is " + event + "\n");
            System.out.println("the data is " + json.toJSONString() + "\n");
        }
        //when onmessage with string data
        @Override
        public void onMessage(String data) {
            System.out.println("data from server : " + data);
        }
        //when onmessage with jsonObject data
        @Override
        public void onMessage(JSONObject msg) {
            System.out.println("data from server = " + msg.toJSONString());           
        }
        //when onmessage with string and jsonObject
        @Override
        public void onMessage(String event, JSONObject json) {
            System.out.println("data from server = " + json.toJSONString());  
        }   
        //when client is close request
        @Override
        public void onClose() {
            System.out.println("client is close the request");
        }
        //when error 
        @Override
        public void onError(ParseException ex) {
            System.out.println("something wrong : " + ex.getMessage());
        }
        //when client is disconnect
        @Override
        public void onDisconnect() {
            System.out.println("client is disconnect");
        }
    };
}
