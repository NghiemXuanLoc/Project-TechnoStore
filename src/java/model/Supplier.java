/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class Supplier {
//    [Supplier_id] [int] IDENTITY(1,1) NOT NULL,
//	[Company_name] [nvarchar](200) NOT NULL,
//	[Phone_number] [nvarchar](10) NULL,
//	[Country] [nvarchar](200) NULL,
//	[Deleted] [bit] NOT NULL,
    
    private int supplierId;
    private String companyName;
    private String phoneName;
    private String country;
    private int deleted;

    public Supplier() {
    }

    public Supplier(int supplierId, String companyName, String phoneName, String country, int deleted) {
        this.supplierId = supplierId;
        this.companyName = companyName;
        this.phoneName = phoneName;
        this.country = country;
        this.deleted = deleted;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Supplier{" + "supplierId=" + supplierId + ", companyName=" + companyName + ", phoneName=" + phoneName + ", country=" + country + ", deleted=" + deleted + '}';
    }
}
