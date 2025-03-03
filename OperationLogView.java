package org.hcioroch.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class OperationLogView extends JFrame {
    private JTable logTable;

    public OperationLogView() {
        setTitle("Dziennik Operacji");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        logTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(logTable);
        add(scrollPane, BorderLayout.CENTER);

        loadOperationLog();

        setVisible(true);
    }

    private void loadOperationLog() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Data i Godzina");
        model.addColumn("Typ Operacji");
        model.addColumn("Opis");
        model.addColumn("Maszyna ID");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/salongier", "root", "root");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM dziennikoperacji")) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("ID"),
                        rs.getTimestamp("DataGodzina"),
                        rs.getString("TypOperacji"),
                        rs.getString("Opis"),
                        rs.getObject("MaszynaID")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Błąd ładowania dziennika operacji.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }

        logTable.setModel(model);
    }
}
