package com.zenbrowser.a1.model.FocusProfile;

import com.zenbrowser.a1.model.Website.Site;

public class Profile {
    private int id;
    private String ProfileName;
    private Site Website;

    public Profile(String ProfileName, Site Website) {
        this.ProfileName = ProfileName;
        this.Website = Website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfileName() {
        return ProfileName;
    }

    public void setProfileName(String profileName) {
        this.ProfileName = profileName;
    }

}
