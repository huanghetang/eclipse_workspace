package vip.hht.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 定义一个转换器
 * 当适配器把前台的数据绑定到入参时
 * 把日期格式字符串转换成日期类型
 * S:前台传过来的类型
 * T:转换后需要的类型
 * @author zhoumo
 *
 */
public class DateConverter implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		try{
			DateFormat	dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义一个前台时间格式模板
			Date date = dateFormat.parse(source);
			System.out.println(date.toLocaleString());
			return date;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
