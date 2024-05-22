/**package com.zenbrowser.a1.model.FocusProfile;

import com.zenbrowser.a1.model.Website.Site;
import com.zenbrowser.a1.model.Website.SiteDAO;
import com.zenbrowser.a1.model.util.TestDatabaseConnection;
import com.zenbrowser.a1.model.util.TestDatabaseUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;


class ProfileDAOTest {

    @BeforeAll
    static void setupTests() throws SQLException {
        setupTestDatabase();
    }
    @AfterAll
    static void tearDown() {
        TestDatabaseUtil.dropTable("sites");
        TestDatabaseUtil.dropTable("profiles");
    }

    public static void setupTestDatabase() {
        TestDatabaseUtil.createSitesTableWithData();
        TestDatabaseUtil.createProfilesTableWithData();
    }
    @Test
    void insertProfile() throws SQLException {
        ProfileDAO profileDAO = new ProfileDAO(TestDatabaseConnection.getInstance());
        SiteDAO siteDAO = new SiteDAO(TestDatabaseConnection.getInstance());

        String URL = "https://test.com";
        String siteName = "abc";
        Boolean blockedStatus = Boolean.FALSE;

        Site site =new Site(URL,siteName,blockedStatus);
        site = siteDAO.insertSite(site);

        String profileName = "testProfile";
        Profile profile = new Profile(profileName,site);

        profileDAO.insertProfile(profile);
        Assertions.assertEquals(profileName,profileDAO.getProfileById(profile.getId()).getURL());
    }

    @Test
    void updateProfile() throws SQLException {
        ProfileDAO profileDAO = new ProfileDAO(TestDatabaseConnection.getInstance());
        SiteDAO siteDAO = new SiteDAO(TestDatabaseConnection.getInstance());

        String URL = "https://test.com";
        String siteName = "abc";
        Boolean blockedStatus = Boolean.FALSE;

        Site site =new Site(URL,siteName,blockedStatus);
        site = siteDAO.insertSite(site);

        String profileName = "testProfile";
        Profile profile = new Profile(profileName,site);
        // call insertProfile so profile we have here has generated id.
        profile = profileDAO.insertProfile(profile);

        //update site in db
        String newSiteName = "newName";
        site.setSiteName(newSiteName);
        siteDAO.updateSite(site);

        //update profile with updated site, site table is updated with new name variable
        profileDAO.updateProfile(profile);
        //check new site name retrieved from profile object
        Assertions.assertEquals(newSiteName,profileDAO.getProfileById(profile.getId()).getWebsite().getSiteName());
    }

    @Test
    void deleteProfile() throws SQLException {
        ProfileDAO profileDAO = new ProfileDAO(TestDatabaseConnection.getInstance());
        SiteDAO siteDAO = new SiteDAO(TestDatabaseConnection.getInstance());

        String URL = "https://test.com";
        String siteName = "abc";
        Boolean blockedStatus = Boolean.FALSE;

        Site site =new Site(URL,siteName,blockedStatus);
        site = siteDAO.insertSite(site);

        String profileName = "testProfile";
        Profile profile = new Profile(profileName,site);
        // call insertProfile so profile we have here has generated id.
        profile = profileDAO.insertProfile(profile);
        // delete the row where id is this id
        profileDAO.deleteProfile(profile.getId());
        // try retrieve that row, should return null if successfully deleted
        Assertions.assertNull(profileDAO.getProfileById(profile.getId()));
    }

    @Test
    void getProfileById() throws SQLException {
        ProfileDAO profileDAO = new ProfileDAO(TestDatabaseConnection.getInstance());
        SiteDAO siteDAO = new SiteDAO(TestDatabaseConnection.getInstance());

        String URL = "https://test.com";
        String siteName = "abc";
        Boolean blockedStatus = Boolean.FALSE;

        Site site =new Site(URL,siteName,blockedStatus);
        site = siteDAO.insertSite(site);

        String profileName = "testProfile";
        Profile profile = new Profile(profileName,site);
        // call insertProfile so profile we have here has generated id.
        profile = profileDAO.insertProfile(profile);

        Assertions.assertEquals(profileName,profileDAO.getProfileById(profile.getId()).getURL());
    }
}
 **/