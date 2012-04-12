/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vyn.udp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


/**
 *
 * @author mohamadsofiyani
 */
public interface UDPCallback {
    /*
     * on Message called when the client send message to server
     */
    void onMessage(String event, JSONArray json);
    void onMessage(String event, JSONObject json);
    
    void onMessage(JSONObject json);
    
    void onMessage(String data);
    void onConnect();
    void onDisconnect();
    /*
     * on Close called when server is closed
     */
    void onClose();
    /*
     * on error called when any error in transmit data or receive data
     */
    void onError(ParseException ex);
   
}
