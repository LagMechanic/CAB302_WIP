package com.zenbrowser.a1.model.FocusProfile;

import com.zenbrowser.a1.model.Website.Site;
import javafx.beans.value.ObservableValue;

import java.sql.Date;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class Profile {
    private int id;
    private String user;
    private String profileName;
    private Site website;
    private Time blockTime;
    private Date blockedUntil;


    public Profile(String user, String profileName, Site website, Time blockTime) {
        this.profileName = profileName;
        this.website = website;
        this.user = user;
        this.blockTime = blockTime;
        blockedUntil = new Date(System.currentTimeMillis() +blockTime.getTime());
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

    public Site getWebsite(){return website;}

    public Date getBlockedUntil() { return blockedUntil; }

    public Time getBlockTime() {return blockTime;}

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
