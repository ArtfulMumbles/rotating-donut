//*******************************************************************
//  Driver
//
//  Copyright © 2022 Michael Scutari
//*******************************************************************

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Driver extends JFrame {

    public Driver() {
        System.out.println("Starting Langton's Ants");
        initUI();
    }
    
    private void initUI() {
        
        add(new GamePanel());

        setResizable(false);
        pack();
        
        setTitle("Langton's Ants");    
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = new Driver();
            ex.setVisible(true);
        });
    }
}