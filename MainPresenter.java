package org.hcioroch.presenter;

import org.hcioroch.model.MachineDAO;
import org.hcioroch.view.MachineView;
import org.hcioroch.view.MainView;
import org.hcioroch.view.OperationLogView;

public class MainPresenter {
    private MainView mainView;
    private MachineDAO machineDAO;

    public MainPresenter(MainView mainView, MachineDAO machineDAO) {
        this.mainView = mainView;
        this.machineDAO = machineDAO;
        mainView.setPresenter(this);
    }

    public void showMachineView() {
        new MachineView(new MachinePresenter(machineDAO));
    }

    public void showOperationLogView() {
        new OperationLogView();
    }

    public void exitApplication() {
        System.exit(0);
    }
}
