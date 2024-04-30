package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.model.Authentication.IAuthentication;
import com.zenbrowser.a1.model.BrowserUsage.IHistoryRecordDAO;
import com.zenbrowser.a1.model.FocusProfile.IProfileDAO;
import com.zenbrowser.a1.model.User.IUserDAO;
import com.zenbrowser.a1.model.Website.ISiteDAO;

public abstract class ControllerAbstract {
    protected IUserDAO UserDAO;

    protected ISiteDAO SiteDAO;

    protected IProfileDAO ProfileDAO;

    protected IHistoryRecordDAO HistoryDAO;
}
