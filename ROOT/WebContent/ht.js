//三级联动,依赖jquery	
$(function() {
		//获取省市县数据并追加到select下
		function get(ele, value) {
			$.get("${root}/ajax", "pid=" + value, function(data) {
				$(data).each(
						function() {
							var opt = '	<option value="'+this.id+'">'
									+ this.name + '</option>';
							ele.append(opt);
						});
			}, "json");
		}
		//获取jquery对象
		var pro = $("#province");
		//初始化省
		get(pro, 0);
		//给市和县绑定change事件
		$("#province,#city").change(function() {
			//当前下拉选改变时,清空后面所有同级别的select标签内容,保留一个初值
			$(this).nextAll().prop("length", 1);
			//给下一级select标签赋值
			get($(this).next(), this.value);
		});
	});