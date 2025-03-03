package org.hcioroch.model;

public class Machine {
    private int id;
    private String name;
    private String type;
    private int status;
    private String size;
    private String model;

    public Machine(int id, String name, String type, int status, String size, String model) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
        this.size = size;
        this.model = model;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
}