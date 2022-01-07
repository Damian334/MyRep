package com.revature;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.Test;
import static org.junit.Assert.*;


public class FormTest {

    public Form things = new Form();

    @Test
    public void testHouse1() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        
        String house = things.selectHouse(); 
        System.out.println("house selected: "+house);
        assertEquals(house,things.houses[0]);
        System.setIn(sysInBackup);
    }
        
    @Test
    public void testHouse2() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("-1".getBytes());
        System.setIn(in);
        
        String house = things.selectHouse(); 
        System.out.println("house selected: "+house);
        assertEquals(house,"");
        System.setIn(sysInBackup);
    }
    
    @Test
    public void testSelectRingFollower() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        
        String follower = things.selectRingFollower(); 
        System.out.println("follower selected: "+follower);
        assertEquals(follower,things.follower[0]);
        System.setIn(sysInBackup);
    }
}
