package org.hcioroch.view;

import org.hcioroch.presenter.MainPresenter;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private JButton manageMachinesButton;
    private JButton operationLogButton;
    private JButton exitButton;
    private MainPresenter presenter;

    public MainView() {
        setTitle("Salon Gier - Panel Główny");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        manageMachinesButton = new JButton("Zarządzaj Maszynami");
        operationLogButton = new JButton("Dziennik Operacji");
        exitButton = new JButton("Wyjście");

        add(manageMachinesButton);
        add(operationLogButton);
        add(exitButton);

        manageMachinesButton.addActionListener(e -> presenter.showMachineView());
        operationLogButton.addActionListener(e -> presenter.showOperationLogView());
        exitButton.addActionListener(e -> presenter.exitApplication());

        setVisible(true);
    }

    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }
}
