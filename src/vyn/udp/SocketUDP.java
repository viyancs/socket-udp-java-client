/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vyn.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
* UDP Implementation - socket-udp-java-client
*
* connect to server
* initializing receive data
* 
*
* @author M Sofiyan
* @email msofyancs@gmail.com
* @skypeid viyancs
* if you want to using part of full this code, please don't remove this comment
*
**/
public class SocketUDP implements DgramTransport{
    
    private InetAddress ip;
    private int port;
    private UDPCallback callback;
    DatagramSocket clientSide ;
    
    /**
     * constructor SocketUDP to set DatagramPacket
     * @param buf is buffer message
     * @param length is length of buffer message
     * @param ip is ipAddress from server
     * @param port is port of ipAddress from server
     */
    public SocketUDP(InetAddress ip,int port,UDPCallback callback ){
        this.ip = ip;
        this.port = port;
        this.callback = callback;
    }
    
    public SocketUDP(){};
    
    public UDPCallback getCallback(){
        return this.callback;
    }
    
    public DatagramSocket getSocket(){
        return this.clientSide;
    }
    
    public void initReceive() throws IOException {
        byte[] receiveData = new byte[256];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSide.receive(receivePacket);

        String data = new String(receivePacket.getData(), 0, receivePacket.getLength());
        JSONObject jObj = null;
        try {
            jObj = (JSONObject) new JSONParser().parse(data);
            UDPMessage message = new UDPMessage(jObj, getCallback());
            message.init();
        } catch (ParseException ex) {
            UDPMessage message = new UDPMessage(data.toString(),getCallback());
            message.init();
            //System.out.println("data is not json format");
        }
    }
    


    @Override
    public void connect() {
        try{
            clientSide = new DatagramSocket();
            clientSide.connect(this.ip,this.port);
        }catch(Exception error){
            System.out.print("could't to connect to server because : " + error.getMessage());
        }
    }

    @Override
    public void close() {
        clientSide.close();
        getCallback().onClose();
    }

    @Override
    public void disconnect() {
        clientSide.disconnect();
        getCallback().onDisconnect();
    }
    
    @Override
    public void send(byte[] data) {
        DatagramPacket packet = new DatagramPacket(data,data.length,this.ip,this.port);
        try {
            if(clientSide == null){
                System.out.print("could't to connect to server \n");
            }else{
                getCallback().onConnect();
                clientSide.send(packet);
                System.out.print("client with ip " + this.ip + " sending packet..." + packet.toString() + '\n');
            }
            
        } catch (IOException ex) {
            System.out.print("could't to sending data to server because : " + ex.getMessage());
        }
        
    }

}
