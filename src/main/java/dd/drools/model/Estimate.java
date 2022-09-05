package dd.drools.model;

import java.util.Date;

public class Estimate {
    public String customer;
    public String salesPerson;
    public String estimateDate;
    public Boolean convertedToSale;
    public Date saleDate;

    public Boolean active;
    public Date createdDate;
    public Date modifiedDate;

    public void setConvertedToSale(Boolean b) { this.convertedToSale = b;}
}
