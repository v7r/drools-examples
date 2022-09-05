package dd.drools.model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Job {
    public int id;
    public double saleValue;
    public String jobLocation;
    public String salesPerson;
    public Date scheduleDate;
    public String invoiceNumber;
    public List<JobMaterial> jobMaterials = new ArrayList();

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", saleValue=" + saleValue +
                ", jobLocation='" + jobLocation + '\'' +
                ", salesPerson='" + salesPerson + '\'' +
                ", scheduleDate=" + scheduleDate +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", jobMaterials=" + jobMaterials +
                '}';
    }
}
