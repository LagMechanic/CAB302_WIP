package com.zenbrowser.a1.model.FocusProfile;

import com.zenbrowser.a1.model.Website.Site;

public class Profile {
    private int id;
    private String profileName;
    private Site website;


    public Profile(String profileName, Site website) {
        this.profileName = profileName;
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public Site getWebsite() { return website; }
    public void setWebsite(Site website) {
        this.website = website;
    }

}
