package dd.drools.model;

public class JobMaterial {
    public int id;
    public Material material;
    public Double quantity;
    public Double reservedQuantity;

    @Override
    public String toString() {
        return "JobMaterial{" +
                "id=" + id +
                ", material=" + material +
                ", quantity=" + quantity +
                ", reservedQuantity=" + reservedQuantity +
                '}';
    }
}
