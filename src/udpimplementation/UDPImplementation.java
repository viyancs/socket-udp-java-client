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
 *
 * @author mohamadsofiyani
 */
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
}
