//*******************************************************************
//  GamePanel
//
//  Copyright © 2022 Michael Scutari
//*******************************************************************

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;


import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePanel extends JPanel implements ActionListener {

    private final int DELAY = 32;

    private final int SCREEN_X = 800;
    private final int SCREEN_Y = 800;

    public Timer timer;
    public Donut donut;

    public GamePanel() {
        initPanel();
        this.donut = new Donut(100);
    }

    public void initPanel() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(SCREEN_X, SCREEN_Y));
        System.out.println("-----INIT-----");

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    public void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        donut.paint(g2d, SCREEN_X, SCREEN_Y);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        donut.rotate(0.03, 0.02);
        repaint();  
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

        }
    }
}
