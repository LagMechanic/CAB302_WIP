package com.zenbrowser.a1.model.Browser;

import com.zenbrowser.a1.Controller.BrowserMain;

import com.zenbrowser.a1.model.Authentication.UserAlreadyExists;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class BrowserMainTest {

    private BrowserMain browserMain;

    @Before
    public void setUp() {
        browserMain = new BrowserMain();


        // Mock any other dependencies as needed
    }

    @Test
    public void testLoadPage() {
        try {
            String validUrl = "https://www.qut.edu.au/";
            browserMain.loadPage(validUrl);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

// Add more tests for other methods in BrowserMain as needed