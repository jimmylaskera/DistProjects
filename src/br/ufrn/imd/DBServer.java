package br.ufrn.imd;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

public class DBServer {
    private Databank db;
    public DBServer(String port) {
        db = new Databank();
        String op = null;
        int acc = 0;
        float value = 0f;
        System.out.println("Databank server started");

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
                    acc = Integer.parseInt(tokenizer.nextToken());
                    value = Float.parseFloat(tokenizer.nextToken());
                }

                String reply = null;
                switch (op) {
                    case "criar":
                        db.createAcc(acc);
                        reply = "Conta " + acc + " criada com sucesso.";
                        break;
                    case "saldo":
                        value = db.getBalance(acc);
                        reply = "Saldo atual: " + value;
                        break;
                    case "depot":
                        db.deposit(acc, value);
                        reply = "Operação concluída. Seu saldo atual: " +  db.getBalance(acc);
                        break;
                }

                System.out.println("Operacao concluida: " +  op + " conta " + acc + " do terminal " + receivedPct.getAddress());
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
