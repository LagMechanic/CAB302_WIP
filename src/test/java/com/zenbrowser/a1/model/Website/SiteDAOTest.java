package com.zenbrowser.a1.model.Website;

import com.zenbrowser.a1.model.util.TestDatabaseConnection;
import com.zenbrowser.a1.model.util.TestDatabaseUtil;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
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
    static void tearDown(){
        TestDatabaseUtil.dropTable("sites");
    }

    public static void setupTestDatabase() {
        TestDatabaseUtil.createSitesTableWithData();
    }
    @Test
    void insertSite() throws SQLException {
        SiteDAO dao = new SiteDAO(TestDatabaseConnection.getInstance());

        String URL = "https://test.com";
        String siteName = "abc";
        Boolean blockedStatus = Boolean.FALSE;

        Site site =new Site(URL,siteName,blockedStatus);
        site = dao.insertSite(site);

        Assertions.assertNotNull(dao.getSiteById(site.getId()));
        Assertions.assertEquals(siteName,dao.getSiteById(site.getId()).getSiteName());
    }
    @Test
    void updateSite() throws SQLException {
        SiteDAO dao = new SiteDAO(TestDatabaseConnection.getInstance());
        //declare test variables and instantiate test site
        String URL = "https://test.com";
        String siteName = "abc";
        Boolean blockedStatus = Boolean.FALSE;
        String updatedSiteName = "def";
        Site site =new Site(URL,siteName,blockedStatus);
        //insert test site
        site = dao.insertSite(site);
        //update site with new name
        site.setSiteName(updatedSiteName);
        dao.updateSite(site);
        //check if retrieved site has updated site name
        Assertions.assertEquals(updatedSiteName, dao.getSiteById(site.getId()).getSiteName());
    }

    @Test
    void deleteSite() throws SQLException {
        SiteDAO dao = new SiteDAO(TestDatabaseConnection.getInstance());
        //declare test variables and instantiate test site
        String URL = "https://test.com";
        String siteName = "abc";
        Boolean blockedStatus = Boolean.FALSE;
        Site site =new Site(URL,siteName,blockedStatus);
        //insert test site
        site = dao.insertSite(site);

        //delete site we just made from database
        dao.deleteSite(site.getId());
        //test if row can be retrieved
        Assertions.assertNull(dao.getSiteById(site.getId()));

    }

    @Test
    void getSiteById() throws SQLException {
        SiteDAO dao = new SiteDAO(TestDatabaseConnection.getInstance());
        //declare test variables and instantiate test site
        String URL = "https://test.com";
        String siteName = "abc";
        Boolean blockedStatus = Boolean.FALSE;
        Site site = new Site(URL,siteName,blockedStatus);

        site = dao.insertSite(site);
        // make sure getSiteById returns the same site with same siteName
        Assertions.assertEquals(siteName,dao.getSiteById(site.getId()).getSiteName());
    }
}