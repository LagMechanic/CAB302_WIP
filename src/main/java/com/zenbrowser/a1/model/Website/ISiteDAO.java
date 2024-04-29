package com.zenbrowser.a1.model.Website;

public interface ISiteDAO {
    Site insertSite(Site site);
    void updateSite(Site site);
    void deleteSite(int id);
    Site getSiteById(int id);

}

