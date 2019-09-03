package com.company.Test;

import com.company.Model.Rival;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class RivalTest {
    Rival rival=new Rival(0,0,0);
    @Test
    public void testCollisionWithPlayer() {
        Assert.assertTrue(rival.testCollisionWithPlayer(new Rectangle(100,75,200,100)));
    }
}