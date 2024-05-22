package com.zenbrowser.a1.model.Website;

import com.zenbrowser.a1.model.SqliteConnection;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
                    + "siteName VARCHAR,"
                    + "category VARCHAR"
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
            statement.setString(1, site.getURL());
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
            statement.setString(1, site.getURL());
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

    @Override
    public Site getSiteByURL(String url) {
        url = parseURL(url);
        System.out.println("get site for url: " + url);

        String sql = "SELECT * FROM sites WHERE url LIKE ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,url);
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

    /**
     * Parses a url to its domain, e.g. https://www.site.com/anything/anything -> site.com
     * @param url url to parse
     * @return domain name; if already valid, returns url
     */
    public static String parseURL(String url) {
        System.out.println("parse: " + url + "->");

        Pattern p = Pattern.compile("(https?://)?(www\\.)?([^/]+)(/.*)?");
        Matcher m = p.matcher(url);
        if (m.find()) {
            url = m.group(3);
            System.out.println("match: " + url);
        }
        return url;
    }

    private Site extractSiteFromResultSet(ResultSet resultSet) throws SQLException {
        Site site = new Site(resultSet.getString("URL"), resultSet.getString("siteName"), resultSet.getString("category"), true);
        site.setId(resultSet.getInt("id"));
        site.setCategory(resultSet.getString("category"));
        return site;
    }
}