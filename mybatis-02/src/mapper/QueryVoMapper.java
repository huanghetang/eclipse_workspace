package mapper;

import java.util.List;

import pojo.QueryVo;
import pojo.User;

public interface QueryVoMapper {
	/**
	 * @param queryVo
	 * @return
	 */
	List<User> findUserByQueryVo(QueryVo queryVo);
	
	/**
	 * 需求:查询用户表数据条数
	 */
	int findUserCount();
	
	/**
	 * 查询订单表order的所有数据
	 */
}
