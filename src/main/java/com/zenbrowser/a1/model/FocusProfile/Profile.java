package com.zenbrowser.a1.model.FocusProfile;

import com.zenbrowser.a1.model.Website.Site;

import java.sql.Date;

public class Profile {
    private int id;
    private String profileName;
    private Site website;
    private Date blockedUntil;


    public Profile(String profileName, Site website, Date blockedUntil) {
        this.profileName = profileName;
        this.website = website;
        this.blockedUntil = blockedUntil;
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

    public Date getBlockedUntil() { return blockedUntil; }
}
