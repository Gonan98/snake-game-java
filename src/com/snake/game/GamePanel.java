package com.snake.game;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.snake.game.BodyPart.Direction;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    private Snake snake;
    private Apple apple;
    private int score;
    private Timer timer;
    private Random random;
    private JButton resetBtn;
    private boolean running;
    
    public GamePanel() {
        score = 0;
        random = new Random();
        resetBtn = new JButton("Reiniciar");
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
        
        this.setPreferredSize(new Dimension(GameConfig.WIDTH, GameConfig.HEIGHT));
        this.setBackground(Color.BLACK);
        this.addKeyListener(new SnakeKeyListener());
        this.setFocusable(true);
        this.add(resetBtn);
        this.start();
    }

    private void start() {
        running = true;
        score = 0;
        resetBtn.setVisible(false);
        int rndX = random.nextInt(GameConfig.WIDTH_UNIT_SIZE - 1) * GameConfig.UNIT_SIZE;
        int rndY = random.nextInt(GameConfig.HEIGHT_UNIT_SIZE - 1) * GameConfig.UNIT_SIZE;
        apple = new Apple(rndX, rndY);
        snake = new Snake(5 * GameConfig.UNIT_SIZE,  3 * GameConfig.UNIT_SIZE);
        timer = new Timer(GameConfig.DELAY, this);
        timer.start();
    }
    
    private class SnakeKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            if (e.getKeyCode() == KeyEvent.VK_UP && snake.getDirection() != Direction.DOWN) {
                snake.setDirection(Direction.UP);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN && snake.getDirection() != Direction.UP) {
                snake.setDirection(Direction.DOWN);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && snake.getDirection() != Direction.LEFT) {
                snake.setDirection(Direction.RIGHT);
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT && snake.getDirection() != Direction.RIGHT) {
                snake.setDirection(Direction.LEFT);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        apple.draw(g);
        snake.draw(g);

        g.setColor(new Color(128,0,0));
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        g.drawString("Puntaje : " + score, 128, 32);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (running) {
            if (snake.bodyCollide()) {
                running = false;
                resetBtn.setVisible(true);
                timer.stop();
            }
            checkSnakeOutOfLimits();
            checkAppleCollision();
            snake.move();
            snake.checkDir();
        }
        repaint();

    }

    public void checkSnakeOutOfLimits() {
        int width = this.getBounds().width;
        int height = this.getBounds().height;

        if (snake.getHeadX() < 0 || snake.getHeadX() > width || snake.getHeadY() < 0 || snake.getHeadY() > height ) {
            running = false;
            resetBtn.setVisible(true);
            timer.stop();
        }
    }

    public void checkAppleCollision() {
        if (snake.getHeadX() == apple.getX() && snake.getHeadY() == apple.getY()) {
            int rndX = random.nextInt(GameConfig.WIDTH_UNIT_SIZE - 1) * GameConfig.UNIT_SIZE;
            int rndY = random.nextInt(GameConfig.HEIGHT_UNIT_SIZE - 1) * GameConfig.UNIT_SIZE;
            apple.setX(rndX);
            apple.setY(rndY);
            score++;
            System.out.printf("%d;%d\n",rndX,rndY);
            snake.grow();
        }
    }
    
}
