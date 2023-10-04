package org.example;

import java.io.IOException;
import java.net.*;

public class UDPClient {
        private DatagramSocket socket;
        private InetAddress address;
        private byte[] buf;

    public UDPClient() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("10.57.37.183");
    }

    public String sendEcho(String msg) throws IOException {
            buf = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
            socket.send(packet);
            String received = new String( packet.getData(), 0, packet.getLength());
            return received;
        }

        public void close(){
            socket.close();
        }

}
