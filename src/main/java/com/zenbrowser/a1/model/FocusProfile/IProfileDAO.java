package com.zenbrowser.a1.model.FocusProfile;

public interface IProfileDAO {

    Profile insertProfile(Profile profile);
    void updateProfile(Profile profile);
    void deleteProfile(int id);
    void getProfile(int id);

}
