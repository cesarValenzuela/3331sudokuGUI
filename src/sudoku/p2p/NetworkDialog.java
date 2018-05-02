package sudoku.p2p;

import javax.swing.*;
import java.awt.*;

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

//    public NetworkDialog(JFrame owner){
//        super(owner, true);
//        setTitle("Connection");
//
//        dialogPanel = new DialogPanel();
//        getContentPane().setLayout(new BorderLayout());
//        getContentPane().add(dialogPanel, BorderLayout.NORTH);
//        pack();
//    }

    private JPanel makePlayerPanel(){
        JPanel panel = new JPanel();

        JLabel hostName = new JLabel("Host name: ");
        JTextField nameField = new JTextField();

        JLabel ipNumber = new JLabel("IP number: ");
        JTextField ipField = new JTextField();

        JLabel portNum = new JLabel("Port number; ");
        JTextField portField = new JTextField();

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

        JButton connect = new JButton("connect");
        JButton disconnect = new JButton("disconnect");

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

//    class DialogPanel extends JPanel{
//        JPanel playerPanel;
//        JPanel peerPanel;
//
//        JLabel hostName;
//        JTextField nameField;
//        JLabel ipNumber;
//        JTextField ipField;
//        JLabel portNum;
//        JTextField portField;
//
//        JLabel peerName;
//        JTextField peerField;
//        JLabel pPortNum;
//        JTextField pPortField;
//        JButton connect;
//        JButton disconnect;
//
//        JTextArea textArea;
//        ButtonGroup buttonGroup;
//
//        DialogPanel(){
//            setLayout(new GridLayout(3,2));
//            playerPanel = new JPanel();
//            hostName = new JLabel("Host name/IP: ");
//            nameField = new JTextField();
//            hostName.setBounds(10,10,60,30);
//            nameField.setBounds(70,15,270,20);
//            ipNumber = new JLabel("IP number: ");
//            ipField = new JTextField();
//            portNum = new JLabel("Port number; ");
//            portField = new JTextField();
//            playerPanel.add(hostName);
//            playerPanel.add(nameField);
//            playerPanel.add(ipNumber);
//            playerPanel.add(ipField);
//            playerPanel.add(portNum);
//            playerPanel.add(portField);
//            playerPanel.setBorder(BorderFactory.createTitledBorder("Player"));
//
//
//            peerPanel = new JPanel();
//            peerName = new JLabel("Host name/IP: ");
//
//            peerField = new JTextField();
//            pPortNum = new JLabel("Port number; ");
//            pPortField = new JTextField();
//            connect = new JButton("connect");
//            disconnect = new JButton("disconnect");
//            peerPanel.add(peerName);
//            peerPanel.add(peerField);
//            peerPanel.add(pPortNum);
//            peerPanel.add(pPortField);
//            peerPanel.add(connect);
//            peerPanel.add(disconnect);
//
//            buttonGroup = new ButtonGroup();
//            buttonGroup.add(connect);
//            buttonGroup.add(disconnect);
//
//            textArea = new JTextArea();
//
//            add(playerPanel);
//            add(peerPanel);
//            add(textArea);
//            doLayout();
//        }
//
//        public Dimension getPreferredSize(){
//            return new Dimension(350, 200);
//        }
//
//        @Override
//        public Dimension getMinimumSize() {
//            return new Dimension(350, 200);
//        }
//
//        @Override
//        public void doLayout() {
//            hostName.setBounds(10,10,60,30);
//            nameField.setBounds(70,15,270,20);
//            ipNumber.setBounds(10,10,60,30);
//            ipField.setBounds(70,15,270,20);
//            portNum.setBounds(10,10,60,30);
//            portField.setBounds(70,15,270,20);
//
//            peerName.setBounds(10,10,60,30);
//            peerField.setBounds(70,15,270,20);
//            pPortNum.setBounds(10,10,60,30);
//            pPortField.setBounds(70,15,270,20);
//
//            playerPanel.setBounds(10,140,330,50);
//            peerPanel.setBounds(10,140,330,50);
//
//
//        }
//    }
}
