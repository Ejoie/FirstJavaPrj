package com.company;
import java.util.*;
import java.net.*;
import java.io.*;

public class ServerThread extends Thread{
    // ============================================
// Class ServerThread
// ============================================

        ServerSocket ss = null;
        Socket s = null;

        // ============================================
        // ServerThread
        // ============================================
        public ServerThread(ServerSocket sSocket)
        {
            ss = sSocket;
        }

        // ============================================
        // run
        // ============================================
        public void run() {
            Dancers d = new Dancers();
            InputStream is;
            OutputStream os;
            String answer;
            try {
                s = ss.accept();
            } catch (Exception ex) {
                stop();
            }

            System.out.println("Connected.");

            try {
                is = s.getInputStream();
                os = s.getOutputStream();


                // Прием команд, их обработка и передача результата клиенту
                while (true) {
                    String szStr = recvString(is);
                    System.out.println(szStr);
                    if (szStr.equals("quit"))
                        break;
                    answer=d.request(szStr);
                    System.out.println(answer);
                    sendString(os, answer);
                    os.flush();

                }

                is.close();
                os.close();
            } catch (Exception ex) {
                stop();
            }

            try {
                s.close();
                ss.close();
            } catch (Exception ex) {
                stop();
            }
        }


    static void sendString(OutputStream os, String s) throws IOException {
        for(int i = 0; i < s.length(); i++)
        {
            os.write((byte)s.charAt(i));
        }
        os.write('\n');
        os.flush();
    }

    // ============================================
    // recvString
    // выполняет посимвольное чтение из входного потока is класса InputStream и //возвращает строку, сформированную из прочитанных символов:
    // ============================================
    static String recvString(InputStream is)
            throws IOException
    {
        String szBuf = "";
        int ch = is.read();

        while (ch >= 0 && ch != '\n')
        {
            szBuf += (char)ch;
            ch = is.read();
        }
        return szBuf;
    }
}



