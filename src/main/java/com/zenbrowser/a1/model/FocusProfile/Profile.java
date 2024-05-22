package com.zenbrowser.a1.model.FocusProfile;

import java.sql.Date;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class Profile {
    private int id;
    private String user;
    private String profileName;
    private String siteURL;
    private String siteName;
    private String category;
    private Time blockTime;
    private Time blockedUntil;


    public Profile(String user, String profileName, String website, Time blockTime) {
        this.profileName = profileName;
        this.siteURL = website;
        this.user = user;
        this.blockTime = blockTime;
        blockedUntil = new Time(System.currentTimeMillis() + blockTime.getTime());
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
    public String getProfileUser() { return user;}

    public String getSiteURL(){return siteURL;}

    public Time getBlockedUntil() { return blockedUntil; }

    public Time getBlockTime() { return blockTime;}

    /**
     * Returns a string array of the remaining time formatted [hrs, mins]
     */
    public String[] getBlockedDuration() {

        long blockedDuration = blockedUntil.getTime() - System.currentTimeMillis();
        // Limit has expired
        if (blockedDuration < 0) {
            return null;
        }
        long mins = (TimeUnit.MILLISECONDS.toMinutes(blockedDuration) % 60);
        long hrs =  TimeUnit.MILLISECONDS.toHours(blockedDuration);
        return new String[] {Long.toString(hrs), Long.toString(mins)};
    }

    public boolean isBlocked() {
        long blockedDuration = blockedUntil.getTime() - System.currentTimeMillis();
        return blockedDuration > 0;
    }
}
