package org.hcioroch.model;

import org.hcioroch.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MachineDAO{
    private static final String INSERT_MACHINE = "INSERT INTO Maszyny (Nazwa, Typ, Status, Rozmiar, Model) VALUES (?, ?, ?, ?, ?);";
    private static final String UPDATE_MACHINE = "UPDATE Maszyny SET Nazwa=?, Typ=?, Status=?, Rozmiar=?, Model=? WHERE ID=?;";
    private static final String DELETE_MACHINE = "DELETE FROM Maszyny WHERE ID=?;";
    private static final String SELECT_ALL_MACHINES = "SELECT * FROM Maszyny;";


    public void addMachine(Machine machine) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_MACHINE)) {
            stmt.setString(1, machine.getName());
            stmt.setString(2, machine.getType());
            stmt.setInt(3, machine.getStatus());
            stmt.setString(4, machine.getSize());
            stmt.setString(5, machine.getModel());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateMachine(Machine machine) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_MACHINE)) {
            stmt.setString(1, machine.getName());
            stmt.setString(2, machine.getType());
            stmt.setInt(3, machine.getStatus());
            stmt.setString(4, machine.getSize());
            stmt.setString(5, machine.getModel());
            stmt.setInt(6, machine.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteMachine(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_MACHINE)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Machine> getAllMachines() {
        List<Machine> machines = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_MACHINES);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                machines.add(new Machine(
                        rs.getInt("ID"),
                        rs.getString("Nazwa"),
                        rs.getString("Typ"),
                        rs.getInt("Status"),
                        rs.getString("Rozmiar"),
                        rs.getString("Model")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return machines;
    }
}
