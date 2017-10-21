/**
 * Created by K_Verdant on 2016-7-3.
 */
var addressInit = function(_cmbProvince, _cmbCity, _cmbArea, defaultProvince, defaultCity, defaultArea)
{
    var cmbProvince = document.getElementById(_cmbProvince);
    /*var cmbCity = document.getElementById(_cmbCity);
    var cmbArea = document.getElementById(_cmbArea);*/

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

    /*function changeCity()
    {
        cmbArea.options.length = 0;
        if(cmbCity.selectedIndex == -1)return;
        var item = cmbCity.options[cmbCity.selectedIndex].obj;
        for(var i=0; i<item.areaList.length; i++)
        {
            cmbAddOption(cmbArea, item.areaList[i], null);
        }
        cmbSelect(cmbArea, defaultArea);
    }*/
    function changeProvince()
    {
        /*cmbCity.options.length = 0;
        cmbCity.onchange = null;*/
        if(cmbProvince.selectedIndex == -1)return;
        var item = cmbProvince.options[cmbProvince.selectedIndex].obj;
        /*for(var i=0; i<item.cityList.length; i++)
        {
            cmbAddOption(cmbCity, item.cityList[i].name, item.cityList[i]);
        }
        cmbSelect(cmbCity, defaultCity);
        changeCity();
        cmbCity.onchange = changeCity;*/
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

    {name:'请选择'}, {name: '北京市'}, {name: '天津市'}, {name: '河北省'}, {name: '山西省'},
    {name: '内蒙古自治区'}, {name: '辽宁省'}, {name: '吉林省'}, {name: '黑龙江省'}, {name: '上海市'},
    {name: '江苏省'}, {name: '浙江省'}, {name: '安徽省'}, {name: '福建省'}, {name: '江西省'}, {name: '山东省'},
    {name: '河南省'}, {name: '湖北省'}, {name: '广东省'}, {name: '广西壮族自治区'}, {name: '海南省'},
    {name: '重庆市'}, {name: '四川省'}, {name: '贵州省'}, {name: '云南省'}, {name: '西藏自治区'},
    {name: '陕西省'}, {name: '甘肃省'}, {name:'青海省'}, {name: '宁夏回族自治区'},
    {name: '新疆维吾尔自治区'}, {name: '台湾省'}, {name: '香港特别行政区'},
    {name: '澳门特别行政区'}

];