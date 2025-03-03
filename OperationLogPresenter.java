package org.hcioroch.presenter;

import org.hcioroch.model.OperationLogDAO;
import java.util.List;

public class OperationLogPresenter {
    private final OperationLogDAO operationLogDAO;

    public OperationLogPresenter() {
        this.operationLogDAO = new OperationLogDAO();
    }

    public List<String[]> fetchOperationLogs() {
        return operationLogDAO.getOperationLogs();
    }
}