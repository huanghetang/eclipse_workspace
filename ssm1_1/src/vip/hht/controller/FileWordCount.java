package vip.hht.controller;
  
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;  
  
/** 
 * javaʵ�ֶ�ȡӢ�����£�ͳ������ÿ�����ʳ��ֵĴ�������� 
 * @author halbert 
 * 
 */  
public class FileWordCount {  
      
    public void count() throws IOException{  
        BufferedReader reader = new BufferedReader(new FileReader("D:\\1.txt"));  
        StringBuffer buffer = new StringBuffer();  
        String line = null;  
        while( (line = reader.readLine()) != null ){  
            buffer.append(line);  
        }  
        reader.close();  
        Pattern expression = Pattern.compile("[a-zA-Z]+");  //����������ʽƥ�䵥��  
        String string = buffer.toString();  
        Matcher matcher = expression.matcher(string);  
        Map<String ,Integer> map = new TreeMap<String, Integer>();  
        String word = "";  
        int n = 0;  
        Integer times = 0;  
        while(matcher.find()){      //�Ƿ�ƥ�䵥��  
            word = matcher.group();     //�õ�һ�����ʣ���ӳ���еļ�  
            n++;  
            if( map.containsKey(word) ){    //����ü����ڣ����ʾ���ʳ��ֹ�  
                times = map.get(word);      //�õ����ʳ��ֵĴ���  
                map.put(word, times+1);  
            } else {  
                map.put(word, 1);   //���򵥴��ǵ�һ�γ��֣�ֱ�ӷ���map  
            }  
        }  
        List<Map.Entry<String ,Integer>> list = new ArrayList<Map.Entry<String ,Integer>>(map.entrySet());  
//        Comparator<Map.Entry<String ,Integer>> comparator = new Comparator<Map.Entry<String ,Integer>>(){  
//  
//            @Override  
//            public int compare(Entry<String ,Integer> left, Entry<String ,Integer> right) {  
//                return (left.getValue()).compareTo(right.getValue());  
//            }  
//              
//        };  
//        Collections.sort(list, comparator);  
        System.out.println("ͳ�Ʒ������£�");  
        System.out.println("�����е�������" + n + "��");  
        System.out.println("�������Ϣ��ԭ�ļ�Ŀ¼��result.txt�ļ���");  
        BufferedWriter bufw = new BufferedWriter(new FileWriter("D:\\result1.txt"));  
        for(Entry<String ,Integer> me : list){  
            bufw.write(me+"");  
            bufw.newLine();  
        }  
        bufw.write("�����еĵ�������" + n + "��");  
        bufw.newLine();  
        bufw.write("�����в�ͬ����" + map.size() + "��");  
        bufw.close();  
    }  
  
      
    public static void main(String[] args) {  
        try {  
            FileWordCount fwc = new FileWordCount();  
            fwc.count();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
    }  
  
}  