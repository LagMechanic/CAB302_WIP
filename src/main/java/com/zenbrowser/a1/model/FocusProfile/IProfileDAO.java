package com.zenbrowser.a1.model.FocusProfile;

import com.zenbrowser.a1.model.Website.Site;

import java.sql.SQLException;
import java.util.List;

public interface IProfileDAO {

    Profile insertProfile(Profile profile);
    void updateProfile(Profile profile);
    void deleteProfile(int id) throws SQLException;
    Profile getProfile(int id);
    List<Profile> getUserProfiles(String username);

    Profile getProfileByNameAndSite(String profileName, Site site);
}
