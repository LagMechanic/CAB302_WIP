package com.zenbrowser.a1.model.Website;

public class Site {
    private int id;
    private String URL;
    private String SiteName;
    private String Category;
    private boolean blocked;

    public Site(String ProfileName, String SiteName, boolean blocked) {
        this.URL = ProfileName;
        this.SiteName = SiteName;
        this.blocked = blocked;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getProfileName() {
        return URL;
    }

    public void setProfileName(String profileName) {
        this.URL = profileName;
    }

}
