package br.ufrn.imd;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

public class SafeServer {
    private Safe safe;
    public SafeServer(String port) {
        safe = new Safe(10000, 0);
        String op = null;
        float value = 0;
        System.out.println("Safe server started");

        try {
            DatagramSocket server = new DatagramSocket(Integer.parseInt(port));
            while (true) {
                byte[] receivedMsg = new byte[1024];
                DatagramPacket receivedPct = new DatagramPacket(receivedMsg, receivedMsg.length);
                server.receive(receivedPct);

                String msg = new String(receivedPct.getData());
                StringTokenizer tokenizer = new StringTokenizer(msg, ";");
                while (tokenizer.hasMoreTokens()) {
                    op = tokenizer.nextToken();
                    value = Integer.parseInt(tokenizer.nextToken());
                }

                String reply = null;
                switch (op) {
                    case "total":
                        reply = "Valor total na maquina atual: "+ safe.getTotal()
                            + "\nValor disponivel: "+ safe.getMoneyAvailable()
                            + "\nValor empacotado: "+ safe.getMoneyBundled();
                        break;
                    case "mover":
                        safe.setMoneyAvailable(safe.getMoneyAvailable()+value);
                        reply = "Valor disponivel atual: " + safe.getMoneyAvailable();
                        break;
                    case "depct":
                        value = safe.getMoneyBundled();
                        safe.setMoneyBundled(0);
                        safe.setMoneyAvailable(safe.getMoneyAvailable()+value);
                        reply = "Operação concluída. Valor disponivel atual: " + safe.getMoneyAvailable();
                        break;
                }

                System.out.println("Operacao concluida: " +  op + " do terminal " + receivedPct.getAddress());
                byte[] replyMsg = reply.getBytes();
                receivedPct.setData(replyMsg);
                receivedPct.setLength(replyMsg.length);
                server.send(receivedPct);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
