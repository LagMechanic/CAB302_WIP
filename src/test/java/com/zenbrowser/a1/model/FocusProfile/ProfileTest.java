package com.zenbrowser.a1.model.FocusProfile;

import com.zenbrowser.a1.model.Website.Site;
import org.junit.jupiter.api.*;

import java.sql.Date;
import java.util.concurrent.TimeUnit;


public class ProfileTest {
    static Profile P;
    static Site S;
    static long Now;
    static Date NowPlus1hr10min;


    @BeforeAll
    static void setup(){
        S = new Site("www.example.com", "example", "testCategory", true);
        Now = System.currentTimeMillis();
        NowPlus1hr10min = new Date(Now + TimeUnit.HOURS.toMillis(1) + TimeUnit.MINUTES.toMillis(10));
        P = new Profile("name", S, NowPlus1hr10min);
    }

    @Test
    void testGetBlockedDuration() {
        String[] blockedDuration = P.getBlockedDuration();
        // check hours
        Assertions.assertEquals(blockedDuration[0], "1");
        // check minutes, assume less than one minute has passed since the limit was initialised
        Assertions.assertEquals(blockedDuration[1], "9");
    }

    @Test
    void testIsBlocked() {
        boolean isBlocked = P.isBlocked();
        Assertions.assertTrue(isBlocked);
    }
}
