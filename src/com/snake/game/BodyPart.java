package com.snake.game;

import java.awt.*;

public class BodyPart {

    public enum Direction { UP, DOWN, RIGHT, LEFT }

    private int x;
    private int y;
    private int speed;
    private Direction direction;
    
    public BodyPart() {
        
    }

    public BodyPart(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = GameConfig.UNIT_SIZE;
        this.direction = Direction.RIGHT;
    }

    public BodyPart(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.speed = GameConfig.UNIT_SIZE;
        this.direction = direction;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0,128,0));
        g.fillRect(x, y, GameConfig.UNIT_SIZE, GameConfig.UNIT_SIZE);
    }

    public void move() {
        switch (direction) {
            case UP:
                y -= speed;
                break;

            case DOWN:
                y += speed;
                break;

            case RIGHT:
                x += speed;
                break;

            case LEFT:
                x -= speed;
                break;

            default:
                break;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
