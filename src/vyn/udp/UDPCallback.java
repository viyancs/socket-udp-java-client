/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vyn.udp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


/**
* UDP Callback - socket-udp-java-client
*
* creating interface for callback
*
* @author M Sofiyan
* @email msofyancs@gmail.com
* @skypeid viyancs
* if you want to using part of full this code, please don't remove this comment
*
**/
public interface UDPCallback {
    /*
     * on Message called when the client send message to server
     * on Connect called when client connect to server
     * on disconnect called when client is disconnect from server
     * on close when client close the request to server
     * on error when any error request and response
     */
    void onMessage(String event, JSONArray json);
    void onMessage(String event, JSONObject json);   
    void onMessage(JSONObject json);   
    void onMessage(String data);
    void onConnect();
    void onDisconnect();
    void onClose();
    void onError(ParseException ex);
   
}
