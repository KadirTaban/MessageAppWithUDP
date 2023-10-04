package org.example;

import java.io.IOException;
import java.net.*;

public class UDPServer extends Thread {

        private DatagramSocket socket;
        private boolean isRun;
        private byte[] buf = new byte[256];

        public UDPServer() throws SocketException {
            socket = new DatagramSocket(4445);
        }

        public void run() {
            isRun = true;

            while (isRun){
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                try {
                    socket.receive(packet);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet=new DatagramPacket(buf, buf.length, address, port);

                String received = new String(packet.getData(), 0, packet.getLength());

                if(received.equals("end")) {
                    isRun = false;
                    continue;
                }

                try {
                    socket.send(packet);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            socket.close();
        }
}

