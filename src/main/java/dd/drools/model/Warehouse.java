package dd.drools.model;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    public int id;
    public String name;
    public List<Inventory> inventory = new ArrayList<>();
}
