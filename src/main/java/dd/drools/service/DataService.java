package dd.drools.service;

import dd.drools.model.*;
import dd.drools.model.event.ReserveJobMaterialReq;

import java.util.*;

import static dd.drools.toolkit.Utils.logMessage;

public class DataService {

    public static final String MATERIAL_CARDBOARD = "Cardboard";
    public static final String MATERIAL_STRIP_FLOORING = "Strip Flooring";

    public static final Warehouse warehouse = new Warehouse();
    public static final Set<Material> materialUniverse = new HashSet<Material>();

    public static void bootstrap() {
        warehouse.id = 102;
        warehouse.name = "Calgary warehouse";

        Material stripFlooring = new Material();
        stripFlooring.description = MATERIAL_STRIP_FLOORING;
        stripFlooring.pricePerUnit = 10.0;
        stripFlooring.unitOfMeasure = "1 piece";
        materialUniverse.add(stripFlooring);

        Material cardboard = new Material();
        cardboard.description = MATERIAL_CARDBOARD;
        cardboard.pricePerUnit = 6.0;
        cardboard.unitOfMeasure = "square ft.";
        materialUniverse.add(cardboard);

        Inventory stripFlooringInventory = new Inventory();
        stripFlooringInventory.location = "Calgary";
        stripFlooringInventory.material = stripFlooring;
        stripFlooringInventory.quantity = 1200;
        warehouse.inventory.add(stripFlooringInventory);

        Inventory cardboardInventory = new Inventory();
        cardboardInventory.location = "Calgary";
        cardboardInventory.material = cardboard;
        cardboardInventory.quantity = 150;
        warehouse.inventory.add(cardboardInventory);
    }

    public static Optional<Material> fetchMaterial(String materialDesc) {
        return materialUniverse.stream().filter(it -> it.description.equalsIgnoreCase(materialDesc)).findFirst();
    }

    public static Optional<Inventory> fetchInventory(Material material) {
        logMessage("Fetching inventory by material: "+material.id);
        return warehouse.inventory.stream().filter( inv -> inv.material.description.equalsIgnoreCase(material.description)).findFirst();
    }

    public static void reserveMaterial(ReserveJobMaterialReq req) {
        dd.drools.toolkit.Utils.logMessage("Reserving material, request: "+req);
        Inventory inventory = fetchInventory(req.jobMaterial.material).get();
        JobMaterial jobMaterial = req.jobMaterial;
        jobMaterial.reservedQuantity = req.jobMaterial.quantity;
        inventory.quantity =  inventory.quantity - jobMaterial.reservedQuantity;
        System.out.println(jobMaterial.reservedQuantity+" quantity is reserved, remaining inventory: "+ inventory.quantity);
    }

    public static Job createJob(Map data) {
        Job j = new Job();
        j.id = (int)(Math.random() * 10000);
        j.jobLocation = (String)data.get("jobLocation");
        j.invoiceNumber = (String)data.get("invoiceNumber");
        j.saleValue = (Double)data.get("saleValue");
        j.jobMaterials = (List<JobMaterial>)data.get("jobMaterials");
        return j;
    }
}
