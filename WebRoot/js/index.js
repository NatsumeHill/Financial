
$(function () {
    $('.toggle-nav').click(function () {
        toggleNavigation();
    });
});


// The toggleNav function itself
function toggleNavigation() {
    if ($('#container-main').hasClass('display-nav')) {
        $('#container-main').removeClass('display-nav');
    } else {
        $('#container-main').addClass('display-nav');
    }
}


// SLiding codes
$("#toggle > li > div").click(function () {
    if (false == $(this).next().is(':visible')) {
        $('#toggle ul').slideUp();
    }

    var $currIcon=$(this).find("span.the-btn");

    $("span.the-btn").not($currIcon).addClass('fa-chevron-down').removeClass('fa-chevron-up');

    $currIcon.toggleClass('fa-chevron-up fa-chevron-down');

    $(this).next().slideToggle();

    $("#toggle > li > div").removeClass("active");
    $(this).addClass('active');

});

var nav=document.getElementById("toggle");
var navLi=document.getElementsByTagName("li");
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

/*function resetFrame()
{
    var container=document.getElementById("container-main");
    var fullWidth=parseInt(getComputedStyle(container,null).width);
    var nav=document.getElementById("nav-menu");
    var frame=document.getElementById("nav-main-window");
    frame.style.width=fullWidth-parseInt(getComputedStyle(nav,null).width)+"px";
};

<script>
window.onload=function ()
{
    var container=document.getElementById("container-main");
    var fullWidth=parseInt(getComputedStyle(container,null).width);
    var nav=document.getElementById("nav-menu");
    var iframe=document.getElementById("nav-main-window");
    iframe.style.width=fullWidth-parseInt(getComputedStyle(nav,null).width)+"px";
};
</script>*/