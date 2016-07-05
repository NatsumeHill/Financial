/**
 * Created by K_Verdant on 2016-7-4.
 */

var myChart = echarts.init(document.getElementById('chart'));
var chartOption = {
    title: {
        text: '可贷款额度'
    },
    tooltip: {},
    legend: {
        data: ['信用度']
    },
    radar: {
        // shape: 'circle',
        indicator: [
            { name: '身份特质', max: 100},
            { name: '信用历史', max: 100},
            { name: '还款能力', max: 100}
        ]
    },
    series: [{
        name: '可贷款额度',
        type: 'radar',
        // areaStyle: {normal: {}},
        data : [
            {
                value : [70, 80, 80],
                name : '信用度'
            }
        ]
    }]
};
myChart.setOption(chartOption);