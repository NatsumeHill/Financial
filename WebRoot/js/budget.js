/**
 * Created by K_Verdant on 2016-7-2.
 */
var budgetChart = echarts.init(document.getElementById('budget-pic'));
var budgetOption = {
    title : {
        text:'本月结余',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: ['结余','已用']
    },
    series : [
        {
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                {value:335, name:'结余'},
                {value:1548, name:'已用'}
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
budgetChart.setOption(budgetOption);

var surplusChart = echarts.init(document.getElementById('surplus-pic'));
var labelRight = {
    normal: {
        position: 'right'
    }
};
var surplusOption ={
    title: {
        text: '每月结余情况'
    },
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        top: 60,
        bottom: 50
    },
    xAxis: {
        type : 'value',
        position: 'top',
        splitLine: {lineStyle:{type:'dashed'}},
    },
    yAxis: {
        type : 'category',
        axisLine: {show: false},
        axisLabel: {show: false},
        axisTick: {show: false},
        splitLine: {show: false},
        data : ['12月','11月','10月', '9月', '8月', '7月', '6月', '5月', '4月', '3月', '2月', '1月']
    },
    series : [
        {
            name:'生活费',
            type:'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    formatter: '{b}'
                }
            },
            data:[
                0.21, 0.35,
                {value: -0.07, label: labelRight},
                {value: -0.09, label: labelRight},
                0.2, 0.44,
                {value: -0.23, label: labelRight},
                0.08,
                {value: -0.17, label: labelRight},
                0.47,
                {value: -0.36, label: labelRight},
                0.18
            ]
        }
    ]
};

surplusChart.setOption(surplusOption);