package com.company.Test;

import com.company.Model.Player;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class PlayerTest {
    Player player=new Player();
    @Test
    public void update() {
        int startS=player.getS();
        int startSpeed=player.getSpeed();
        int startY=player.getY();
        player.setDv(1);
        player.setDy(10);
        player.update();
        Assert.assertTrue(player.getS()-player.getSpeed()==startS);
        Assert.assertTrue(player.getSpeed()-1==startSpeed);
        Assert.assertTrue(player.getY()+10==startY);
    }
    @Test
    public void createShoot() {
        int startSize=player.getShootsSize();
        player.createShoot();
        Assert.assertTrue(player.getShootsSize()-1==startSize);

    }
    @Test
    public void checkAttack() {
        player.createShoot();
        int numOfShoots=player.getShootsSize();
        Assert.assertTrue(player.checkAttack(0,new Rectangle(200,250,400,300)));
        Assert.assertTrue(numOfShoots-1==player.getShootsSize());
    }
}