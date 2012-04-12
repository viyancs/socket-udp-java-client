/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vyn.udp;

/**
* UDP Implementation - socket-udp-java-client
*
* interface for DatagramSocket
*
* @author M Sofiyan
* @email msofyancs@gmail.com
* @skypeid viyancs
* if you want to using part of full this code, please don't remove this comment
*
**/
public interface DgramTransport {
    void connect();
    void close();
    void disconnect();
    void send(byte[] packet);
            
}
