package sudoku.p2p;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Author: Cesar Valenzuela
 * Date: 5/1/2018
 * Course:
 * Assignment:
 * Instructor:
 * T.A:
 */
public class NetworkDialog extends JDialog {

    JPanel player;
    JPanel peer;
    JPanel bottom;

    JButton connect;
    JButton disconnect;

    JTextArea eventBox;
    //DialogPanel dialogPanel;

    public NetworkDialog() {

    }

    public NetworkDialog(JFrame owner){
        super(owner, true);
        setTitle("Connection");
        setResizable(false);

        eventBox = new JTextArea(10,32);
        eventBox.setEditable(false);
        bottom = new JPanel();
        bottom.add(eventBox);
        bottom.setBorder(BorderFactory.createEtchedBorder());

        player = makePlayerPanel();

        peer = makePeerPanel();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(bottom, BorderLayout.SOUTH);
        getContentPane().add(peer, BorderLayout.CENTER);
        getContentPane().add(player, BorderLayout.NORTH);
        pack();

    }

    private JPanel makePlayerPanel(){
        JPanel panel = new JPanel();

        JLabel hostName = new JLabel("Host name: ");
        JTextField nameField = new JTextField("localhost");
        nameField.setEnabled(false);

        JLabel ipNumber = new JLabel("IP number: ");
        JTextField ipField = new JTextField();
        ipField.setEnabled(false);

        JLabel portNum = new JLabel("Port number; ");
        JTextField portField = new JTextField();
        portField.setEnabled(false);

        hostName.setBounds(10,10,60,30);
        nameField.setBounds(70,15,270,20);

        panel.setBorder(BorderFactory.createTitledBorder("Player"));
        panel.setLayout(new GridLayout(3,2,5,5));
        panel.add(hostName);
        panel.add(nameField);
        panel.add(ipNumber);
        panel.add(ipField);
        panel.add(portNum);
        panel.add(portField);

        return panel;
    }

    private JPanel makePeerPanel(){
        JPanel panel = new JPanel();

        JLabel hostName = new JLabel("Host name/IP: ");
        JTextField nameField = new JTextField(10);

        JLabel portNum = new JLabel("Port number; ");
        JTextField portField = new JTextField(10);

        connect = new JButton("connect");

        disconnect = new JButton("disconnect");


        panel.setBorder(BorderFactory.createTitledBorder("Peer"));
        panel.setLayout(new GridLayout(3,2,5,5));
        panel.add(hostName);
        panel.add(nameField);
        panel.add(portNum);
        panel.add(portField);
        panel.add(connect);
        panel.add(disconnect);

        return panel;

    }

    public void addConnectListener(ActionListener listener){
        connect.addActionListener(listener);
    }


}
