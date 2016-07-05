/**
 * Created by K_Verdant on 2016-7-2.
 */
var addressInit = function(_cmbProvince, _cmbCity, _cmbArea, defaultProvince, defaultCity, defaultArea)
{
    var cmbProvince = document.getElementById(_cmbProvince);
    var cmbCity = document.getElementById(_cmbCity);
    var cmbArea = document.getElementById(_cmbArea);

    function cmbSelect(cmb, str)
    {
        for(var i=0; i<cmb.options.length; i++)
        {
            if(cmb.options[i].value == str)
            {
                cmb.selectedIndex = i;
                return;
            }
        }
    }
    function cmbAddOption(cmb, str, obj)
    {
        var option = document.createElement("OPTION");
        cmb.options.add(option);
        option.innerHTML = str;
        option.value = str;
        option.obj = obj;
    }

    function changeCity()
    {
        cmbArea.options.length = 0;
        if(cmbCity.selectedIndex == -1)return;
        var item = cmbCity.options[cmbCity.selectedIndex].obj;
        for(var i=0; i<item.areaList.length; i++)
        {
            cmbAddOption(cmbArea, item.areaList[i], null);
        }
        cmbSelect(cmbArea, defaultArea);
    }
    function changeProvince()
    {
        cmbCity.options.length = 0;
        cmbCity.onchange = null;
        if(cmbProvince.selectedIndex == -1)return;
        var item = cmbProvince.options[cmbProvince.selectedIndex].obj;
        for(var i=0; i<item.cityList.length; i++)
        {
            cmbAddOption(cmbCity, item.cityList[i].name, item.cityList[i]);
        }
        cmbSelect(cmbCity, defaultCity);
        changeCity();
        cmbCity.onchange = changeCity;
    }

    for(var i=0; i<provinceList.length; i++)
    {
        cmbAddOption(cmbProvince, provinceList[i].name, provinceList[i]);
    }
    cmbSelect(cmbProvince, defaultProvince);
    changeProvince();
    cmbProvince.onchange = changeProvince;
};

var provinceList = [

    {name:'请选择', cityList:[
        {name:'请选择', areaList:['请选择']}
    ]},

    {name:'支出', cityList:[
        {name:'请选择', areaList:['请选择']},
        {name:'娱乐', areaList:['请选择','游戏','电影','音乐']},
        {name:'购物', areaList:['请选择','服饰','丽人','日用品','家居']},
        {name:'出行', areaList:['请选择','住宿','交通','旅行']},
        {name:'餐饮', areaList:['请选择','用餐','食材','酒水','零食']},
        {name:'生活', areaList:['请选择','通讯','水电','物业']},
        {name:'一般', areaList:['请选择','还款','社交','答谢']},
        {name:'医疗', areaList:['请选择','药品','住院','检查']},
        {name:'其它', areaList:['请选择','其它']}
    ]},


    {name:'收入', cityList:[
        {name:'请选择'},
        {name:'工资'},
        {name:'理财'},
        {name:'收款'},
        {name:'其它'}
    ]}
];