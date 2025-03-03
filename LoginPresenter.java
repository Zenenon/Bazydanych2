package org.hcioroch.presenter;

import org.hcioroch.model.Machine;
import org.hcioroch.model.UserDAO;

public class LoginPresenter {
    private UserDAO userDAO;

    public LoginPresenter(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean login(String username, String password) {
        return userDAO.validateUser(username, password);
    }
}