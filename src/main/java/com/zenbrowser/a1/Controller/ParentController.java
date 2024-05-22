package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.model.BrowserUsage.HistoryRecordDAO;
import com.zenbrowser.a1.model.BrowserUsage.IHistoryRecordDAO;
import com.zenbrowser.a1.model.FocusProfile.IProfileDAO;
import com.zenbrowser.a1.model.FocusProfile.ProfileDAO;
import com.zenbrowser.a1.model.User.IUserDAO;
import com.zenbrowser.a1.model.User.SqliteUserDAO;

public abstract class ParentController {
    protected IUserDAO UserDAO;
    protected IProfileDAO ProfileDAO;
    protected IHistoryRecordDAO HistoryDAO;
    private static String currentUser;

    public ParentController()
    {
        UserDAO = new SqliteUserDAO();
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