package sudoku.p2p;

import sudoku.dialog.SudokuDialog;
import sudoku.p2p.net.NetworkAdapter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Author: Cesar Valenzuela
 * Date: 4/27/2018
 * Course:
 * Assignment:
 * Instructor:
 * T.A:
 */
public class Main extends SudokuDialog implements NetworkAdapter.MessageListener {

    private ImageIcon NETWORK_OFF, NETWORK_ON;
    private JButton networkButton;
    private NetworkAdapter network;
    private JDialog dialog;

    public Main(){
        super();
        dialog = new NetworkDialog(this);
        dialog.setVisible(false);
        dialog.setBounds(400,0,400,400);
        //configureUI();
    }

    @Override
    protected JToolBar createToolBar(){
        JToolBar toolBar = super.createToolBar();
        NETWORK_OFF = createImageIcon("wifi-red.png");
        networkButton = new JButton(NETWORK_OFF);
        networkButton.addActionListener(this::networkButtonClicked);
        networkButton.setToolTipText("Pair");
        networkButton.setFocusPainted(false);
        toolBar.add(networkButton, toolBar.getComponentCount() - 1);

        return toolBar;
    }

    private void networkButtonClicked(ActionEvent e){
        dialog.setVisible(true);
        new Thread(()->{
            try{
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress("127.0.0.1", 8000),5000);

            } catch (Exception ex){}
        }).start();
    }

    private void pairAsClient(Socket socket){
        network = new NetworkAdapter(socket);
        network.setMessageListener(this);
        network.writeJoin();
        network.receiveMessages();
    }


    @Override
    public void messageReceived(NetworkAdapter.MessageType type, int x, int y, int z, int[] others) {

    }

    public static void main(String[] args) {
        new Main();
    }
}
