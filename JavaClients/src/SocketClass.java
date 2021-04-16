import  java.net.*;
import  java.io.*;
import  java.util.*;

public class SocketClass {

    Socket s ;
    InputStream is;
    OutputStream os;

    public SocketClass(String host, int port) throws IOException {
        this.s = new Socket(host, port);
        this.is = s.getInputStream();
        this.os = s.getOutputStream();
    }

     public void closeConnect() throws IOException {
        is.close();
        os.close();
        s.close();
    }

    public String senderi(String answer) {
            try {
                sendString(os, answer);
                os.flush();
                answer = recvString(is);
            } catch (Exception ex) {
                answer = ex.toString();
            }
        return answer;
    }

    // ============================================
    // sendString
    // ============================================
    static void sendString(OutputStream os,
                           String s)
            throws IOException
    {
        for(int i = 0; i < s.length(); i++)
        {
            os.write((byte)s.charAt(i));
        }
        os.write('\n');
        os.flush();
    }

    // ============================================
    // recvString
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

    // ============================================
    // getKbdString
    // ============================================
    static public String getKbdString()
    {
        byte bKbd[] = new byte[256];
        int iCnt = 0;
        String szStr = "";

        try
        {
            iCnt = System.in.read(bKbd);
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }

        szStr = new String(bKbd, 0, iCnt);
        szStr = szStr.trim();
        return szStr;
    }


}
