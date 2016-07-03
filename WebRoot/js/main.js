/**
 * Created by K_Verdant on 2016-7-1.
 */
var incomeChart = echarts.init(document.getElementById('income'));
$.ajax({
	url : '../bill/jsonInOut',
	type : 'GET',
	dataType : 'json',
}).done(function(jsonData) {
	var incomeOption = {
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b}: {c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			data : jsonData.out.categories
		},
		series : [ {
			name : '访问来源',
			type : 'pie',
			selectedMode : 'single',
			radius : [ 0, '30%' ],

			label : {
				normal : {
					position : 'inner'
				}
			},
			labelLine : {
				normal : {
					show : false
				}
			},
			data : jsonData.out.data.billFlag_1
		}, {
			name : '访问来源',
			type : 'pie',
			radius : [ '40%', '55%' ],

			data : jsonData.out.data.billFlag_2
		} ]
	};
	incomeChart.setOption(incomeOption);

    var payChart = echarts.init(document.getElementById('pay'));
var payOption = {
    tooltip : {
        trigger : 'item',
        formatter : "{a} <br/>{b}: {c} ({d}%)"
    },
    legend : {
        orient : 'vertical',
        x : 'left',
        data : jsonData.in.categories
    },
    series : [ {
        name : '访问来源',
        type : 'pie',
        radius : [ '50%', '70%' ],
        avoidLabelOverlap : false,
        label : {
            normal : {
                show : false,
                position : 'center'
            },
            emphasis : {
                show : true,
                textStyle : {
                    fontSize : '30',
                    fontWeight : 'bold'
                }
            }
        },
        labelLine : {
            normal : {
                show : false
            }
        },
        data : jsonData.in.data
    } ]
};
payChart.setOption(payOption);

}).fail(function() {
	console.log("error");
}).always(function() {
	console.log("complete");
});


$.ajax({
	url: '../bill/jsonTrend',
	type: 'GET',
	dataType: 'json',
	data: {year: new Date().getFullYear()},
})
.done(function(data) {
	var trendChart = echarts.init(document.getElementById('trend-pic'));
var trendOption = {
	tooltip : {
		trigger : 'axis'
	},
	legend : {
		data : [ '消费趋势' ]
	},
	grid : {
		left : '3%',
		right : '4%',
		bottom : '3%',
		containLabel : true
	},
	xAxis : [ {
		type : 'category',
		boundaryGap : false,
		data : data.month
	} ],
	yAxis : [ {
		type : 'value'
	} ],
	series : [ {
		name : '消费趋势',
		type : 'line',
		stack : '总量',
		areaStyle : {
			normal : {}
		},
		data : data.money
	} ]
};
trendChart.setOption(trendOption);
	console.log("success");
})
.fail(function() {
	console.log("error");
})
.always(function() {
	console.log("complete");
});



var analysisChart = echarts.init(document.getElementById('analysis-pic'));
var analyOption = {
	tooltip : {},
	legend : {
		data : [ '预算分配（Allocated Budget）', '实际开销（Actual Spending）' ]
	},
	radar : {
		// shape: 'circle',
		indicator : [ {
			name : '销售（sales）',
			max : 6500
		}, {
			name : '管理（Administration）',
			max : 16000
		}, {
			name : '信息技术（Information Techology）',
			max : 30000
		}, {
			name : '客服（Customer Support）',
			max : 38000
		}, {
			name : '研发（Development）',
			max : 52000
		}, {
			name : '市场（Marketing）',
			max : 25000
		} ]
	},
	series : [ {
		name : '预算 vs 开销（Budget vs spending）',
		type : 'radar',
		// areaStyle: {normal: {}},
		data : [ {
			value : [ 4300, 10000, 28000, 35000, 50000, 19000 ],
			name : '预算分配（Allocated Budget）'
		}, {
			value : [ 5000, 14000, 28000, 31000, 42000, 21000 ],
			name : '实际开销（Actual Spending）'
		} ]
	} ]
};
analysisChart.setOption(analyOption);

var overageChart = echarts.init(document.getElementById('overage-pic'));
var overageOption = {
	tooltip : {
		formatter : "{a} <br/>{b} : {c}%"
	},
	series : [ {
		type : 'gauge',
		radius : '95%',
		detail : {
			formatter : '{value}%',
			width : 390,
			height : 250
		},
		data : [ {
			value : 45
		} ]
	} ]
};
overageChart.setOption(overageOption);