package vip.hht.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;


/**
 * springmvc 不能识别并转化时间类型参数,所以要自定义转换器,转换成时间.把页面的参数绑定到方法中的形参是HandlerAdapter做的,所以需要在配置适配器时指定转换时间需要的类
 * 实现Converter接口
 * s:页面中需要转化前的数据类型
 * T:转换后的数据类型
 * @author zhoumo
 *
 */
public class DateConverter implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		try{
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(source);
		}catch(ParseException e){
			e.printStackTrace();
		}
		return null;
	}
	

}
