package vip.hht.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import vip.hht.beans.PageParam;
import vip.hht.beans.Product;
import vip.hht.beans.ProductExample;

public interface ProductMapper {
    int countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(String pid);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(String pid);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
    
    
    /**
     * 商品分页查询
     */
    List<Product> select4PageBean(PageParam pageParam);

    /**
     * 批量删除
     * @param pids
     * @return
     */
	void batchDel(Integer[] pids);
    
    

}