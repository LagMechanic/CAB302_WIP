package com.zenbrowser.a1.model.Website;



public interface ISiteDAO {
    void insertSite(Site site);
    void updateSite(Site site);
    void deleteSite(int id);
    Site getSiteByID(int id);
}
