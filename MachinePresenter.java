package org.hcioroch.presenter;

import org.hcioroch.model.*;

import java.util.List;

public class MachinePresenter {
    private MachineDAO machineDAO;

    public MachinePresenter(MachineDAO machineDAO) {
        this.machineDAO = machineDAO;
    }

    public void addMachine(Machine machine) {
        machineDAO.addMachine(machine);
    }

    public void updateMachine(Machine machine) {
        machineDAO.updateMachine(machine);
    }

    public void deleteMachine(int id) {
        machineDAO.deleteMachine(id);
    }

    public List<Machine> getAllMachines() {
        return machineDAO.getAllMachines();
    }
}
