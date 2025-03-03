package org.hcioroch;

import org.hcioroch.model.MachineDAO;
import org.hcioroch.model.UserDAO;
import org.hcioroch.presenter.LoginPresenter;
import org.hcioroch.presenter.MainPresenter;
import org.hcioroch.view.LoginView;
import org.hcioroch.view.MainView;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class MainApp {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        LoginPresenter loginPresenter = new LoginPresenter(userDAO);
        LoginView loginView = new LoginView(loginPresenter);

        if (loginPresenter.login("admin", "admin123")) {  // Symulacja logowania, do zastąpienia rzeczywistym procesem
            MachineDAO machineDAO = new MachineDAO();
            MainView mainView = new MainView();
            new MainPresenter(mainView, machineDAO);
        }
    }
}