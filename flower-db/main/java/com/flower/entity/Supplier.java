package com.flower.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/4/29.
 */
@Entity
public class Supplier {
    private Integer supId;
    private String supName;
    private String supAddress;
    private String supTel;
    private String supEamil;
    private List<Purchase> purchases;

    @Id
    @Column(name = "sup_id", nullable = false)
    @GeneratedValue
    public Integer getSupId() {
        return supId;
    }

    public void setSupId(Integer supId) {
        this.supId = supId;
    }

    @Column(name = "sup_name", nullable = false, length = 50)
    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }


    @Column(name = "sup_address", nullable = true, length = 100)
    public String getSupAddress() {
        return supAddress;
    }

    public void setSupAddress(String supAddress) {
        this.supAddress = supAddress;
    }


    @Column(name = "sup_tel", nullable = true, length = 20)
    public String getSupTel() {
        return supTel;
    }

    public void setSupTel(String supTel) {
        this.supTel = supTel;
    }


    @Column(name = "sup_eamil", nullable = true, length = 50)
    public String getSupEamil() {
        return supEamil;
    }

    public void setSupEamil(String supEamil) {
        this.supEamil = supEamil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return supId == supplier.supId &&
                Objects.equals(supName, supplier.supName) &&
                Objects.equals(supAddress, supplier.supAddress) &&
                Objects.equals(supTel, supplier.supTel) &&
                Objects.equals(supEamil, supplier.supEamil);
    }

    @Override
    public int hashCode() {

        return Objects.hash(supId, supName, supAddress, supTel, supEamil);
    }

    @OneToMany(mappedBy = "supplier")
    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
}
