package com.flower.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/4/29.
 * 商品类别
 */
@Entity
public class Category implements Serializable {

    private static final long serialVersionUID = 3381563345442793504L;
    private Integer categoryId; //分类编号
    private String categoryCode;//分类编码
    private String categoryName;//分类名称
    private String catagoryDesc;//分类描述
    private Integer parentId; //父类别id
    private List<Goods> goodsList;


    @Transient
    private String checked = "false";//是否选中

    private int level; //层级

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
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Category category = (Category) o;
//        return categoryId == category.categoryId &&
//                Objects.equals(categoryCode, category.categoryCode) &&
//                Objects.equals(categoryName, category.categoryName) &&
//                Objects.equals(catagoryDesc, category.catagoryDesc) &&
//                Objects.equals(parentId, category.parentId);
//    }
//
//    @Override
//    public int hashCode() {
//
//        return Objects.hash(categoryId, categoryCode, categoryName, catagoryDesc, parentId);
//    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "GoodsCategory", joinColumns = {@JoinColumn(name = "categoryId")}, inverseJoinColumns = {@JoinColumn(name = "goodsId")})
    @JsonBackReference
    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }


    @Transient
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryCode='" + categoryCode + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", catagoryDesc='" + catagoryDesc + '\'' +
                ", parentId=" + parentId +
                ", checked='" + checked + '\'' +
                '}';
    }
}
