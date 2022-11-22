package com.zetcode.view;

import com.zetcode.business.model.Player;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Snake extends JFrame {

    private static JFrame ex;

    public Snake() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
               
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            ex = new Snake();
            ex.setVisible(true);
        });
    }

    public static void savePlayer(){

        NewPlayer newPlayer = new NewPlayer(ex, true);
        newPlayer.setVisible(true);

    }

}
