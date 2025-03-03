package org.hcioroch.view;

import org.hcioroch.presenter.LoginPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;
    private LoginPresenter presenter;

    public LoginView(LoginPresenter presenter) {
        this.presenter = presenter;
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Zaloguj");
        statusLabel = new JLabel(" ", SwingConstants.CENTER);

        add(new JLabel("Nazwa użytkownika:"));
        add(usernameField);
        add(new JLabel("Hasło:"));
        add(passwordField);
        add(loginButton);
        add(statusLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        setVisible(true);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (presenter.login(username, password)) {
            statusLabel.setText("Logowanie udane!");
            JOptionPane.showMessageDialog(this, "Zalogowano pomyślnie.");
            dispose(); // Zamknięcie okna po zalogowaniu
        } else {
            statusLabel.setText("Nieprawidłowe dane logowania.");
            JOptionPane.showMessageDialog(this, "Błąd logowania.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }
}
