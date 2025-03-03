package org.hcioroch.model;

import org.hcioroch.database.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperationLogDAO {
    public List<String[]> getOperationLogs() {
        List<String[]> logs = new ArrayList<>();
        String query = "SELECT ID, DataGodzina, TypOperacji, Opis FROM dziennikoperacji ORDER BY DataGodzina DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                logs.add(new String[]{
                        rs.getString("ID"),
                        rs.getString("DataGodzina"),
                        rs.getString("TypOperacji"),
                        rs.getString("Opis")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }
}