package com.company.Test;

import com.company.Model.Boss;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;


public class BossTest {
    Boss boss=new Boss();
    @Test
    public void createShoot() {
            int startSize=boss.getShootsSize();
            boss.createShoot();
            Assert.assertTrue(boss.getShootsSize()-1==startSize);

    }

    @Test
    public void checkAttack() {
        boss.createShoot();
        Assert.assertTrue(boss.checkAttack(new Rectangle(300,200,400,500)));
    }
}