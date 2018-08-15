package mapper;

import java.util.List;

import pojo.QueryVo;
import pojo.User;

public interface UserMapper {

		User findUserById(Integer id);
		List<User> findUserByName(String name);
		void updateUser(User user);
		void deleteUserById(Integer id);
		Integer addUser(User user);
		/**
		 * 根据性别和名字查询用户
		 */
		public abstract List<User> findUserBySexAndName(User user);
		
		/**
		 *根据多个id查询用户信息 
		 */
		List<User> findUsersByIds(QueryVo queryVo);
		
		/**
		 * 根据多个用户id[]数组查询信息 
		 */
		public abstract List<User> findUsersByIdArrayList(QueryVo queryVo);
		
		/**
		 * 根据用户数组查询用户 直接传数组
		 */
		public abstract List<User> findUsersByArray(int[] ids);
		/**
		 * 根据用户数组查询用户 直接传集合
		 */
		public abstract List<User> findUsersByList(List<Integer> ids);
		
		/**
		 * 查询所有用户信息及用户关联的订单信息。
		 */
		List<User> findUserAndOrder();
}	

