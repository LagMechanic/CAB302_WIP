package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.model.BrowserUsage.HistoryRecordDAO;
import com.zenbrowser.a1.model.BrowserUsage.IHistoryRecordDAO;
import com.zenbrowser.a1.model.FocusProfile.IProfileDAO;
import com.zenbrowser.a1.model.FocusProfile.ProfileDAO;
import com.zenbrowser.a1.model.User.IUserDAO;
import com.zenbrowser.a1.model.User.SqliteUserDAO;
import com.zenbrowser.a1.model.Website.ISiteDAO;
import com.zenbrowser.a1.model.Website.SiteDAO;

public class ParentController {
    protected IUserDAO UserDAO;
    protected ISiteDAO SiteDAO;
    protected IProfileDAO ProfileDAO;
    protected IHistoryRecordDAO HistoryDAO;

    protected static String currentUser;

    public ParentController()
    {
        UserDAO = new SqliteUserDAO();
        SiteDAO = new SiteDAO();
        ProfileDAO = new ProfileDAO();
        HistoryDAO = new HistoryRecordDAO();
    }

    protected String getCurrentUser(){
        return currentUser;
    }

    protected void setCurrentUser(String currentUser){
        this.currentUser = currentUser;
    }
}
