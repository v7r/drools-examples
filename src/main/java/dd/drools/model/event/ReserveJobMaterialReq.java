package dd.drools.model.event;

import dd.drools.model.Job;
import dd.drools.model.JobMaterial;

public class ReserveJobMaterialReq {
    public Job job;
    public JobMaterial jobMaterial;

    @Override
    public String toString() {
        return "ReserveJobMaterialReq{" +
                "job=" + job +
                ", jobMaterial=" + jobMaterial +
                '}';
    }
}
