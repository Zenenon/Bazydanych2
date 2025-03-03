package org.hcioroch.view;


import org.hcioroch.model.Machine;
import org.hcioroch.presenter.MachinePresenter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MachineView extends JFrame {
    private MachinePresenter presenter;
    private JTable machineTable;
    private DefaultTableModel tableModel;
    private JButton addButton, editButton, deleteButton;

    public MachineView(MachinePresenter presenter) {
        this.presenter = presenter;
        setTitle("Zarządzanie Maszynami");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"ID", "Nazwa", "Typ", "Status", "Rozmiar", "Model"}, 0);
        machineTable = new JTable(tableModel);
        refreshTable();

        add(new JScrollPane(machineTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Dodaj");
        editButton = new JButton("Edytuj");
        deleteButton = new JButton("Usuń");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addMachine());
        editButton.addActionListener(e -> editMachine());
        deleteButton.addActionListener(e -> deleteMachine());

        setVisible(true);
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<Machine> machines = presenter.getAllMachines();
        for (Machine machine : machines) {
            tableModel.addRow(new Object[]{machine.getId(), machine.getName(), machine.getType(), machine.getStatus(), machine.getSize(), machine.getModel()});
        }
    }

    private void addMachine() {
        String name = JOptionPane.showInputDialog(this, "Podaj nazwę maszyny:");
        String type = JOptionPane.showInputDialog(this, "Podaj typ maszyny:");
        String statusStr = JOptionPane.showInputDialog(this, "Podaj status maszyny (liczba):");
        String size = JOptionPane.showInputDialog(this, "Podaj rozmiar maszyny:");
        String model = JOptionPane.showInputDialog(this, "Podaj model maszyny:");

        try {
            int status = Integer.parseInt(statusStr);
            Machine machine = new Machine(0, name, type, status, size, model);
            presenter.addMachine(machine);
            refreshTable();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nieprawidłowy format statusu.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editMachine() {
        int selectedRow = machineTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Wybierz maszynę do edycji.", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String name = JOptionPane.showInputDialog(this, "Edytuj nazwę maszyny:", tableModel.getValueAt(selectedRow, 1));
        String type = JOptionPane.showInputDialog(this, "Edytuj typ maszyny:", tableModel.getValueAt(selectedRow, 2));
        String statusStr = JOptionPane.showInputDialog(this, "Edytuj status maszyny:", tableModel.getValueAt(selectedRow, 3).toString());
        String size = JOptionPane.showInputDialog(this, "Edytuj rozmiar maszyny:", tableModel.getValueAt(selectedRow, 4));
        String model = JOptionPane.showInputDialog(this, "Edytuj model maszyny:", tableModel.getValueAt(selectedRow, 5));

        try {
            int status = Integer.parseInt(statusStr);
            Machine machine = new Machine(id, name, type, status, size, model);
            presenter.updateMachine(machine);
            refreshTable();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nieprawidłowy format statusu.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteMachine() {
        int selectedRow = machineTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Wybierz maszynę do usunięcia.", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        presenter.deleteMachine(id);
        refreshTable();
    }
}
