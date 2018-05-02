package com.flower.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/4/29.
 * 供应商
 */
@Entity
public class Supplier implements Serializable {
    private static final long serialVersionUID = 1780113025926478070L;
    private Integer supId; //编号
    private String supName; //供应商名称
    private String supAddress; //供应商地址
    private String supTel; //供应商电话
    private String supEmail; //供应商邮箱
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
    public String getSupEmail() {
        return supEmail;
    }

    public void setSupEmail(String supEmail) {
        this.supEmail = supEmail;
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
                Objects.equals(supEmail, supplier.supEmail);
    }

    @Override
    public int hashCode() {

        return Objects.hash(supId, supName, supAddress, supTel, supEmail);
    }

    @JsonBackReference
    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY)
    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supId=" + supId +
                ", supName='" + supName + '\'' +
                ", supAddress='" + supAddress + '\'' +
                ", supTel='" + supTel + '\'' +
                ", supEmail='" + supEmail + '\'' +
                '}';
    }
}
