//1.第一步 写以下方法来格式化日期
Date.prototype.format = function (format) {  
    var o = {  
        "M+": this.getMonth() + 1, // month  
        "d+": this.getDate(), // day  
        "h+": this.getHours(), // hour  
        "m+": this.getMinutes(), // minute  
        "s+": this.getSeconds(), // second  
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter  
        "S": this.getMilliseconds()  
        // millisecond  
    }  
    if (/(y+)/.test(format))  
        format = format.replace(RegExp.$1, (this.getFullYear() + "")  
            .substr(4 - RegExp.$1.length));  
    for (var k in o)  
        if (new RegExp("(" + k + ")").test(format))  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
    return format;  
}  

//2.第二步 扩展的Date的format方法(上述插件实现)  
function formatDatebox(value) {  

    if (value == null || value == '') {  
        return '';  
    }  
    var dt;  
    if (value instanceof Date) {  
        dt = value;  
    } else {  
        dt = new Date(value);  
    }  
  
    return dt.format("yyyy-MM-dd"); 
} 

//3.第三部  写好那就用呗 下面绿色的代码

//$('#dg').datagrid({
//    url:'../RampAcLimitFltAction/getAcLimitFltList2.do',
//   columns:[[ 
//           {field:'limitStart',title:'起始时间',width:100,align:'center',formatter: formatDatebox}, 
//           {field:'limitEnd',title:'结束时间',width:100,align:'center',formatter: formatDatebox}
//           ]]
//
//});


//另外一种实现 unixTimestamp.toLocaleString()


/*$("#dg").datagrid({
	url:'orderServlet?methodName=listPendingOrder', // 通ajax完成数据库表的数据加载。
    fit:true,				   // datagrid数据填充父容器
     fitColumns:true 
    pagination:true,		   // 显示分页栏
    rownumbers:true,
    singleSelect:false,
    pageList:[5,10,20],	       // 每页显示记录数
	columns:[[ 
	  		{field:'ck',checkbox:true},
	        {field:'id',title:'订单号',width:270, align:'center'},    
	        {field:'uid',title:'用户ID',width:150, align:'center'},    
	        {field:'totalprice',title:'订单总价',width:100,align:'center'},
	        {field:'address',title:'收件人地址',width:300,align:'center'}, 
	        {field:'acceptperson',title:'收件人',width:150,align:'center'}, 
	        {field:'telephone',title:'手机号',width:150,align:'center'}, 
	        {field:'status',title:'订单状态',width:80,align:'center'},
	        {field:'createtime',title:'订单创建时间',width:200,align:'center',
        		formatter:function(value,row,index){  
            		var unixTimestamp = new Date(value);  
                   	return unixTimestamp.toLocaleString();  
            	}  
	        	
	        }
	  		]]
});*/