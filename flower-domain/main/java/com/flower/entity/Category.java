package com.flower.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/3/25.
 * 商品分类
 */
@Entity
public class Category implements Serializable {
    private int categoryId;
    private String categoryCode; //类别码
    private String categoryName; //类别名
    private String catagoryDesc; //类别描述
    private List<Goods> goods; //商品集合

    @Id
    @Column(name = "category_id", nullable = false)
    @GeneratedValue
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "category_code", nullable = false, length = 20)
    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    @Basic
    @Column(name = "category_name", nullable = false, length = 20)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Basic
    @Column(name = "catagory_desc", nullable = true, length = 200)
    public String getCatagoryDesc() {
        return catagoryDesc;
    }

    public void setCatagoryDesc(String catagoryDesc) {
        this.catagoryDesc = catagoryDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return categoryId == category.categoryId &&
                Objects.equals(categoryCode, category.categoryCode) &&
                Objects.equals(categoryName, category.categoryName) &&
                Objects.equals(catagoryDesc, category.catagoryDesc);
    }

    @Override
    public int hashCode() {

        return Objects.hash(categoryId, categoryCode, categoryName, catagoryDesc);
    }

    @OneToMany(mappedBy = "category")
    public Collection<Goods> getgoods() {
        return goods;
    }

    public void setgoods(List<Goods> goods) {
        this.goods = goods;
    }
}
