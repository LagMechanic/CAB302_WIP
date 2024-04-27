package com.zenbrowser.a1.model.Website;

import com.zenbrowser.a1.model.util.TestDatabaseUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SiteDAOTest {
    @BeforeAll
    static void setupTests(){
        setupTestDatabase();
    }

    @AfterAll
    static void tearDown() throws SQLException {
        //TestDatabaseUtil.dropTable("sites");
    }

    public static void setupTestDatabase() {
        TestDatabaseUtil.createSitesTableWithData();
    }
    @Test
    void insertSite() {
    }

    @Test
    void updateSite() {
    }

    @Test
    void deleteSite() {
    }

    @Test
    void getSiteById() {
    }
}