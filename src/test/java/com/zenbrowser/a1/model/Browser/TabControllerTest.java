package com.zenbrowser.a1.model.Browser;

import com.zenbrowser.a1.Controller.MainControllers.TabController;

import org.junit.Before;
import org.junit.Test;


public class TabControllerTest {

    private TabController tabController;

    @Before
    public void setUp() {
        tabController = new TabController();


        // Mock any other dependencies as needed
    }

    @Test
    public void testLoadPage() {
        try {
            String validUrl = "https://www.qut.edu.au/";
            tabController.loadPage(validUrl);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

    // Add more tests for other methods in BrowserMain as needed

