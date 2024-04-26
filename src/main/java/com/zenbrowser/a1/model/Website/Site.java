package com.zenbrowser.a1.model.Website;

public class Site {
    private int id;
    private String URL;
    private String siteName;
    private String category;
    private boolean blockedStatus;

    public Site(String ProfileName, String siteName, boolean blockedStatus) {
        this.URL = ProfileName;
        this.siteName = siteName;
        this.blockedStatus = blockedStatus;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getProfileName() {
        return URL;
    }
    public String getSiteName() { return siteName;}
    public void setProfileName(String profileName) {
        this.URL = profileName;
    }
    public String getCategory() { return category; }
    public boolean isBlockedStatus() { return isBlockedStatus(); }
}
