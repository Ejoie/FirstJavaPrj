import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import  java.net.*;
import  java.io.*;
import  java.util.*;

public class FirstClient {
    private JPanel Fpanel;
    private JButton scopeButton;
    private JComboBox nameCBox;
    private JComboBox danceCBox;
    private JTextField refereeScope1;
    private JButton allDanceButton;
    private JButton nDanceButton;
    private JTextField refereeScope2;
    private JTextField refereeScope3;
    private JTextField refereeScope4;
    private JTextField refereeScope5;
    private JTextField refereeScope6;
    private JLabel aloneLabel;
    private static SocketClass sc;

    public FirstClient() {
        scopeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                aloneLabel.setText(sc.senderi(
                        "1;"+ String.valueOf(danceCBox.getSelectedIndex()+1) +";"+
                                      String.valueOf(nameCBox.getSelectedIndex()+1) +";"+
                                refereeScope1.getText()+";"+refereeScope2.getText()+";"+refereeScope3.getText()+";"+
                                refereeScope4.getText()+";"+refereeScope5.getText()+";"+refereeScope6.getText()+";"));
                refereeScope1.setText("");
                refereeScope2.setText("");
                refereeScope3.setText("");
                refereeScope4.setText("");
                refereeScope5.setText("");
                refereeScope6.setText("");

            }
        });
        allDanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                aloneLabel.setText(sc.senderi("3;"+ String.valueOf(nameCBox.getSelectedIndex()+1) +";"));
            }
        });
        nDanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                aloneLabel.setText(sc.senderi(
                        "2;"+ String.valueOf(nameCBox.getSelectedIndex()+1) +";"+
                                String.valueOf(danceCBox.getSelectedIndex()+1) +";"));
            }
        });
    }

    public static void main(String[] args) throws IOException {
        JFrame frame= new JFrame("App");
        frame.setContentPane(new FirstClient().Fpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        sc = new SocketClass("localhost",5005);
        }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        try {
            sc.closeConnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
