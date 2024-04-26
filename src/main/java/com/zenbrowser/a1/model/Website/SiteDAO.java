package com.zenbrowser.a1.model.Website;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SiteDAO implements ISiteDAO {

    private final Connection connection;

    public SiteDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertSite(Site site) {
        String sql = "INSERT INTO sites (id, URL, siteName, category, blockedStatus) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,site.getId());
            statement.setString(2, site.getProfileName());
            statement.setString(3, site.getSiteName());
            statement.setString(4, site.getCategory());
            statement.setBoolean(5, site.isBlockedStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateSite(Site site) {
        String sql = "UPDATE sites SET URL=?, siteName=?, category=?, blockedStatus=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, site.getProfileName());
            statement.setString(2, site.getSiteName());
            statement.setString(3, site.getCategory());
            statement.setBoolean(4, site.isBlockedStatus());
            statement.setInt(5, site.getId());
            statement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSite(int id) {

    }

    @Override
    public void getAllSites() {

    }
}
