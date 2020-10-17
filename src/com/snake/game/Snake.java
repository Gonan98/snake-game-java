package com.snake.game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.snake.game.BodyPart.Direction;

public class Snake {

    private List<BodyPart> bodyParts;

    public Snake(int x, int y) {

        bodyParts = new ArrayList<>();

        // Generando el cuerpo
        for (int i = 0; i < 5; i++) {
            bodyParts.add(new BodyPart(x - GameConfig.UNIT_SIZE * i, y));
        }
    }

    public void draw(Graphics g) {
        for (BodyPart bp : bodyParts) {
            bp.draw(g);
        }
    }

    public void move() {
        for (BodyPart bp : bodyParts) {
            bp.move();
        }
    }

    public void grow() {
        BodyPart tail = bodyParts.get(bodyParts.size() - 1);
        
        switch (tail.getDirection()) {
            case UP:
                bodyParts.add(new BodyPart(tail.getX(), tail.getY() + GameConfig.UNIT_SIZE, Direction.UP));
                break;

            case DOWN:
                bodyParts.add(new BodyPart(tail.getX(), tail.getY() - GameConfig.UNIT_SIZE, Direction.DOWN));
                break;

            case RIGHT:
                bodyParts.add(new BodyPart(tail.getX() - GameConfig.UNIT_SIZE, tail.getY(), Direction.RIGHT));
                break;

            case LEFT:
                bodyParts.add(new BodyPart(tail.getX() + GameConfig.UNIT_SIZE, tail.getY(), Direction.LEFT));
                break;

            default:
                break;
        }
    }

    public void checkDir() {
        for (int i = bodyParts.size() - 1; i >= 1; i--) {
            BodyPart actual = bodyParts.get(i);
            BodyPart anterior = bodyParts.get(i - 1);

            actual.setDirection(anterior.getDirection());
        }
    }

    public boolean bodyCollide() {
        
        for (int i = 1; i < bodyParts.size() - 1; i++) {
            BodyPart bodyPart = bodyParts.get(i);
            if (this.getHeadX() == bodyPart.getX() && this.getHeadY() == bodyPart.getY()) {
                return true;
            }
        }

        return false;
    }

    public Direction getDirection() {
        return this.getHead().getDirection();
    }

    public void setDirection(Direction d) {
        this.getHead().setDirection(d);
    }

    private BodyPart getHead() {
        return bodyParts.get(0);
    }

    public int getHeadX() {
        return bodyParts.get(0).getX();
    }

    public int getHeadY() {
        return bodyParts.get(0).getY();
    }
    
}
