package com.zenbrowser.a1.model.Browser;

import com.zenbrowser.a1.Controller.BrowserMain;

import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;


public class BrowserMainTest extends ApplicationTest {

    private BrowserMain browserMain;

    @Before
    public void setUp() {
        browserMain = new BrowserMain();


        // Mock any other dependencies as needed
    }

    @Test
    public void testLoadPage() {
        String validUrl = "https://www.qut.edu.au/";
        browserMain.loadPage(validUrl);
        // Add assertions based on expected behavior when loading a page
    }

}

    // Add more tests for other methods in BrowserMain as needed

