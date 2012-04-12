/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vyn.udp;

/**
 *
 * @author mohamadsofiyani
 */
public interface DgramTransport {
    void connect();
    void close();
    void disconnect();
    void send(byte[] packet);
            
}
