/**
 * Created by K_Verdant on 2016-7-3.
 */
$('#c1').css('float','left');
$('<div id="c2" style="float:left;"></div>').insertAfter('#c1');
var data = [
    {name: '娱乐',value: 10,children: [
        {name: '1月',value: 18},
        {name: '2月',value: 10},
        {name: '3月',value: 7},
        {name: '4月',value: 12}
    ]},
    {name: '购物',value: 8,children: [
        {name: '1月',value: 28},
        {name: '2月',value: 20},
        {name: '3月',value: 7},
        {name: '4月',value: 2}
    ]},
    {name: '出行',value: 4,children: [
        {name: '1月',value: 14},
        {name: '2月',value: 12},
        {name: '3月',value: 6},
        {name: '4月',value: 1}
    ]},
    {name: '生活',value: 9,children: [
        {name: '1月',value: 38},
        {name: '2月',value: 10},
        {name: '3月',value: 17},
        {name: '4月',value: 22}
    ]}
];
var Stat = G2.Stat;
var chart1 = new G2.Chart({
    id: 'c1',
    animate: false,
    width: 500,
    height: 450,
    plotCfg: {
        margin: [100, 80, 80, 60]
    }
});
chart1.source(data);
chart1.coord('theta');
chart1.intervalStack().position(Stat.summary.percent('value')).color('name');
chart1.render();
var chart2 = new G2.Chart({
    id: 'c2', // 直接使用容器，不使用id
    width: 500,
    height: 450,
    plotCfg: {
        margin: [100, 80, 80, 60]
    }
});
function findObj(name) {
    var rst = null;
    data.forEach(function(item){
        if(item.name === name) {
            rst = item;
        }
    });
    return rst;
}
chart1.on('itemselected',function (ev) {
    var data = ev.data;
    var origin = data._origin; // 原始数据
    var name = origin.name; // 由于data.name 被转化成了数字
    var obj = findObj(name);
    chart2.clear();
    chart2.source(obj.children);
    chart2.interval().position('name*value');
    chart2.render();
});
// 也可以监听click事件
chart1.on('plotclick',function(ev){
    var data = ev.data;
    if (data) {
        var origin = data._origin;
    }
});
// 设置默认选中
var geom = chart1.getGeoms()[0]; // 获取所有的图形
var items = geom.getData(); // 获取图形对应的数据
geom.setSelected(items[0]); // 设置选中