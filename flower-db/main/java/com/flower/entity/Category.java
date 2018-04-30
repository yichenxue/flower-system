package com.flower.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/4/29.
 */
@Entity
public class Category {
    private Integer categoryId;
    private String categoryCode;
    private String categoryName;
    private String catagoryDesc;
    private Integer parsentId;
    private List<GoodsCategory> goodsCategories;

    @Id
    @Column(name = "category_id", nullable = false)
    @GeneratedValue
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }


    @Column(name = "category_code", nullable = false, length = 20)
    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }


    @Column(name = "category_name", nullable = false, length = 20)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    @Column(name = "catagory_desc", nullable = true, length = 200)
    public String getCatagoryDesc() {
        return catagoryDesc;
    }

    public void setCatagoryDesc(String catagoryDesc) {
        this.catagoryDesc = catagoryDesc;
    }

    @Column(name = "parsent_id", nullable = true)
    public Integer getParsentId() {
        return parsentId;
    }

    public void setParsentId(Integer parsentId) {
        this.parsentId = parsentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return categoryId == category.categoryId &&
                Objects.equals(categoryCode, category.categoryCode) &&
                Objects.equals(categoryName, category.categoryName) &&
                Objects.equals(catagoryDesc, category.catagoryDesc) &&
                Objects.equals(parsentId, category.parsentId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(categoryId, categoryCode, categoryName, catagoryDesc, parsentId);
    }

    @OneToMany(mappedBy = "category")
    public List<GoodsCategory> getGoodsCategories() {
        return goodsCategories;
    }

    public void setGoodsCategories(List<GoodsCategory> goodsCategories) {
        this.goodsCategories = goodsCategories;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryCode='" + categoryCode + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", catagoryDesc='" + catagoryDesc + '\'' +
                ", parsentId=" + parsentId +
                '}';
    }
}
