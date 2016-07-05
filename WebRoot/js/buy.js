/**
 * Created by K_Verdant on 2016-7-4.
 */
var nav=document.getElementsByClassName("view-page");
var navLi=document.getElementsByTagName("div");
var contentFrame=document.getElementById("main-content");
for(var i=0;i<navLi.length;i++)
{
    navLi[i].index=i;
    navLi[i].onclick=function ()
    {
        var src=this.getAttribute("data-src");
        contentFrame.src=src;
        stopPropagation();
    }
}

function stopPropagation(e) {
    e = e || window.event;
    if(e.stopPropagation) { //W3C阻止冒泡方法
        e.stopPropagation();
    } else {
        e.cancelBubble = true; //IE阻止冒泡方法
    }
}