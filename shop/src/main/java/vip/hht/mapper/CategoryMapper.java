package vip.hht.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vip.hht.beans.Category;
import vip.hht.beans.CategoryExample;
import vip.hht.beans.PageParam;

public interface CategoryMapper {
    int countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(String cid);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(String cid);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    
    List<Category> selectCategory4Page(PageParam pageParam);
    
    
   int updateProductCidNull(String cid);
}