/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vyn.udp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
* UDP Implementation - socket-udp-java-client
*
* give param in contructor to parse message from server
* initializing to filter params that is only string or json type
*
* @author M Sofiyan
* @email msofyancs@gmail.com
* @skypeid viyancs
* if you want to using part of full this code, please don't remove this comment
*
**/
public class UDPMessage {

    private JSONObject jsonData;
    private String message;
    private String type;
    private UDPCallback callback;

    /**
     * contructor 
     * @param json is data from server
     * @param callback is callback 
     */
    public UDPMessage(JSONObject json, UDPCallback callback) {
        this.jsonData = json;
        this.type = "json";
        this.callback = callback;
    }
    /**
     * contructor
     * @param msg is data from server with String type
     * @param callback  is callback
     */
    public UDPMessage(String msg, UDPCallback callback) {
        this.message = msg;
        this.type = "string";
        this.callback = callback;
    }
    /**
     * initializing for filter data from server
     */
    public void init() {
        if (this.type.equals("json")) {
            if (this.jsonData.get("name") == null || this.jsonData.get("args") == null) {
                this.callback.onMessage(this.jsonData);
            } else {
                JSONArray jsonArray;
                JSONObject jsonObject;
                try {
                    System.out.println(this.jsonData.get("args").getClass().toString());
                    if ("class org.json.simple.JSONArray".equals(this.jsonData.get("args").getClass().toString())) {
                        jsonArray = (JSONArray) new JSONParser().parse(this.jsonData.get("args").toString());
                        this.callback.onMessage(this.jsonData.get("name").toString(), jsonArray);
                    } else {
                        jsonObject = (JSONObject) new JSONParser().parse(this.jsonData.get("args").toString());
                        this.callback.onMessage(this.jsonData.get("name").toString(), jsonObject);
                    }
                } catch (ParseException ex) {
                    this.callback.onError(ex);
                }
            }
        } else if (this.type.equals("string")) {
            this.callback.onMessage(this.message);
        }

    }
}
