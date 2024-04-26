package com.zenbrowser.a1.model.Website;

import com.zenbrowser.a1.model.User.User;

import java.util.List;

public interface ISiteDAO {
    void insertSite(Site site);
    void updateSite(Site site);
    void deleteSite(int id);
    void getAllSites();
}
