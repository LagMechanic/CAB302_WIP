package com.zenbrowser.a1.model.Website;

import com.zenbrowser.a1.model.SqliteConnection;

import java.sql.*;

public class SiteDAO implements ISiteDAO {

    private final Connection connection;

    public SiteDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }


    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS sites ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "URL VARCHAR NOT NULL,"
                    + "siteName VARCHAR NOT NULL,"
                    + "category VARCHAR NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Site insertSite(Site site) {
        String sql = "INSERT INTO sites ( URL, siteName, category) VALUES ( ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, site.getProfileName());
            statement.setString(2, site.getSiteName());
            statement.setString(3, site.getCategory());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0){
                System.out.println("inserted record successfully");
                try(var generatedKeys = statement.getGeneratedKeys()){
                    if (generatedKeys.next()){
                        int siteId = generatedKeys.getInt(1);
                        System.out.println("generated key id: " + siteId);
                        site.setId(siteId);
                        return site;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return site;
    }

    @Override
    public void updateSite(Site site) {
        String sql = "UPDATE sites SET URL=?, siteName=?, category=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, site.getProfileName());
            statement.setString(2, site.getSiteName());
            statement.setString(3, site.getCategory());
            statement.setInt(4, site.getId());
            statement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSite(int id) {
        String sql = "DELETE FROM sites WHERE id=?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id);
            statement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Site getSiteById(int id) {
        String sql = "SELECT * FROM sites WHERE id=?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    return extractSiteFromResultSet(resultSet);
                }
                else {
                    return null;
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Site extractSiteFromResultSet(ResultSet resultSet) throws SQLException {
        Site site = new Site(resultSet.getString("URL"), resultSet.getString("siteName"), resultSet.getBoolean("blockedStatus"));
        site.setId(resultSet.getInt("id"));
        site.setCategory(resultSet.getString("category"));
        return site;
    }
}