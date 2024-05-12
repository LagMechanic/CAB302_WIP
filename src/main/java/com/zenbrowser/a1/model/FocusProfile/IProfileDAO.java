package com.zenbrowser.a1.model.FocusProfile;

import java.sql.SQLException;

public interface IProfileDAO {

    Profile insertProfile(Profile profile);
    void updateProfile(Profile profile);
    void deleteProfile(int id) throws SQLException;
    Profile getProfile(int id);

}
