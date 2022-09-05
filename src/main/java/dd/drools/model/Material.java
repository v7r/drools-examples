package dd.drools.model;

public class Material {
    public int id;

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                ", pricePerUnit=" + pricePerUnit +
                '}';
    }

    public String description;
    public String unitOfMeasure;
    public Double pricePerUnit;
}
