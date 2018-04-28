package com.flower.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/3/25.
 * 供应商信息
 */
@Entity
public class Supplier implements Serializable {
    private int supId; //供应商编号
    private String supName; //供应商名称
    private String supAddress; //供应商地址
    private String supTel; //供应商联系方式
    private String supEamil; //供应商邮箱
    private List<Purchase> purchases;

    @Id
    @Column(name = "sup_id", nullable = false)
    @GeneratedValue
    public int getSupId() {
        return supId;
    }

    public void setSupId(int supId) {
        this.supId = supId;
    }

    @Basic
    @Column(name = "sup_name", nullable = false, length = 50)
    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    @Basic
    @Column(name = "sup_address", nullable = true, length = 100)
    public String getSupAddress() {
        return supAddress;
    }

    public void setSupAddress(String supAddress) {
        this.supAddress = supAddress;
    }

    @Basic
    @Column(name = "sup_tel", nullable = true, length = 20)
    public String getSupTel() {
        return supTel;
    }

    public void setSupTel(String supTel) {
        this.supTel = supTel;
    }

    @Basic
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
    public List<Purchase> getpurchases() {
        return purchases;
    }

    public void setpurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
}
