package com.flower.dao;

import com.flower.entity.Category;
import com.flower.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by yumaoying on 2018/4/30.
 */
public interface GoodsDao extends JpaRepository<Goods, Integer>, JpaSpecificationExecutor<Goods> {

    public Page<Goods> findAll(Specification spec, Pageable pageable);

    public Goods findByGoodsId(Integer id);

    public Goods save(Goods goods);

    public void delete(Integer id);

    public List<Goods> findAll();

    //根据商品id查询商品类别
    @Query(value = "select  g.categories from Goods  g where   g.goodsId=?1")
    public List<Category> findCategorysByGoodsId(Integer id);

    //删除商品的分类信息
    @Modifying
    @Query(value = "delete from goods_category  where  goods_id=?1", nativeQuery = true)
    public void deleteCategorys(Integer goodsId);

    //保存商品分类
    @Modifying
    @Query(value = "insert into goods_category(goods_id,category_id) values(?1,?2)", nativeQuery = true)
    public void save(Integer goodsId, Integer category_id);


}
