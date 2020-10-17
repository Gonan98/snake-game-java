package com.snake.game;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private GamePanel panel;

    public GameFrame() {
        this.panel = new GamePanel();
        this.add(panel);
        this.setTitle("Snake Game on Java");
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void start() {

    }
    
}
