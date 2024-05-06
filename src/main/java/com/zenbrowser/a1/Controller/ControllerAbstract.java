package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.model.Authentication.IAuthentication;
import com.zenbrowser.a1.model.BrowserUsage.HistoryRecordDAO;
import com.zenbrowser.a1.model.BrowserUsage.IHistoryRecordDAO;
import com.zenbrowser.a1.model.FocusProfile.IProfileDAO;
import com.zenbrowser.a1.model.FocusProfile.ProfileDAO;
import com.zenbrowser.a1.model.User.IUserDAO;
import com.zenbrowser.a1.model.User.SqliteUserDAO;
import com.zenbrowser.a1.model.Website.ISiteDAO;
import com.zenbrowser.a1.model.Website.SiteDAO;

public abstract class ControllerAbstract {
    protected IUserDAO UserDAO;

    protected ISiteDAO SiteDAO;

    protected IProfileDAO ProfileDAO;

    protected IHistoryRecordDAO HistoryDAO;

    protected ButtonPressedListener buttonPressedListener;

    ControllerAbstract()
    {
        UserDAO = new SqliteUserDAO();
        SiteDAO = new SiteDAO();
        ProfileDAO = new ProfileDAO();
        HistoryDAO = new HistoryRecordDAO();
    }


    public interface ButtonPressedListener {
        void onButtonPressed(String result);
    }
    public void setButtonPressedListener(ButtonPressedListener listener) {
        this.buttonPressedListener = listener;
    }
}
