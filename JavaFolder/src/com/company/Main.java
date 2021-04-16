package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.*;
import java.net.*;
import java.io.*;


public class Main extends Thread {

    public static void main(String[] args) {
        System.out.println("Starting Socket Server");
        ServerSocket ss = null;
        try{
            ss = new ServerSocket(5005);
        }
        catch (Exception ex){
            System.out.println(ex.toString());
            System.exit(0);
        }

        int nPort = ss.getLocalPort();
        System.out.println("Local port: "+nPort);
        ServerThread sThread = null;
        sThread = new ServerThread(ss);
        sThread.start();
        System.out.println("Waiting connection..");

        try
        {
            sThread.join();
        }
        catch(InterruptedException ex)
        {
            System.out.println(ex.toString());
        }

        try
        {
            ss.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }

        System.exit(0);
    }

}
