/**
 * Created by K_Verdant on 2016-7-1.
 */
// Calling the function
$(function() {
	$('.toggle-nav').click(function() {
		toggleNavigation();
        $('#submit').unbind('click', addHandler);
        $('#submit').unbind('click', modifyHandler);
        $('#submit').bind('click', addHandler);
	});
});

$(function() {
	$('.modify-nav').click(function(event) {
        toggleNavigation();
	})
});

// The toggleNav function itself
function toggleNavigation() {
	if ($('#main-container').hasClass('display-nav')) {
		// Close Nav
		$('#main-container').removeClass('display-nav');
	} else {
		// Open Nav
		$('#main-container').addClass('display-nav');
	}
}
var billId = "";
//
var init = false;
//init current page wiht 1
var pageNow = 0;

//
$(function(){
    console.log("init load");
    showPage(1);
});

//change page handler
function showPage(page){
    if(page == pageNow) return;
    pageNow = page;
    $('.page-view').slideUp("400",function(){
        //
        $('.form-view').slideUp("slow",function(){
            $('tbody > tr').remove();
             $.ajax({
            url : '../bill/bills',
            type : 'GET',
            dataType : 'json',
            data : {"pageNow":pageNow}
        })
        .done(
                function(data) {
                    //update table
                    $('.now-total').html("Current:"+data.page.pageNow+"/Total:"+data.page.totalPageCount);
                    for (var i = 0; i < data.bills.length; i++) {
                        $('tbody').append(
                                '<tr billId="' + data.bills[i].billId
                                        + '" class="excel-form"></tr>');
                        $('tr[billId=' + data.bills[i].billId + ']')
                                .append(
                                        '<td class="modify"><a class="modify-nav" id="modify" billId="' + data.bills[i].billId
                                        + '"><i class="fa fa-pencil"></i></a></td>');
                        $('tr[billId=' + data.bills[i].billId + ']').append(
                                '<td>' + data.bills[i].billDate + '</td>');
                        $('tr[billId=' + data.bills[i].billId + ']').append(
                                '<td>' + data.bills[i].billType + '</td>');
                        $('tr[billId=' + data.bills[i].billId + ']').append(
                                '<td>' + data.bills[i].billFlag_1 + '--'
                                        + data.bills[i].billFlag_2 + '</td>');
                        $('tr[billId=' + data.bills[i].billId + ']').append(
                                '<td>' + data.bills[i].billMoney + '</td>');
                        $('tr[billId=' + data.bills[i].billId + ']').append(
                                '<td>'+data.bills[i].billNote+'</td>');
                        $('tr[billId=' + data.bills[i].billId + ']')
                                .append(
                                        '<td class="delete" billId="' + data.bills[i].billId
                                        + '"><a id="delete"><i class="fa fa-trash"></i></a></td>');
                    }
                    
                    //init page-view
                    if(init == false){
                        if (data.page.totalPageCount > 9) {
                        for (var i = 1; i < 10; i++) {
                            $('.pagination')
                                    .append('<li><a id="'+i+'">' + i + '</a></li>');
                        }
                    }
                    $('.pagination')
                            .append(
                                    '<li><a aria-label="Next" id="next"><span aria-hidden="true">&raquo;</span></a></li>');

                    //init message handler
                    $("#1").addClass('selected');
                    $('#pre').click(function(event) {
                        if(pageNow != 1){
                            var id = parseInt( $('.selected').attr('id'))-1;
                            if(id<4 && $('#1').html() != 1){
                                for (var i = 1; i < 10; i++) {
                                    var text = $('#'+i).html();
                                    $('#'+i).html(parseInt(text)-1);
                                }
                            }else{
                                $('.selected').removeClass('selected');
                                $('#'+id).addClass('selected');
                            }
                            showPage(parseInt(pageNow) -1);
                        }
                    });
                    $('#next').click(function(event) {
                        if(pageNow != data.page.totalPageCount){ 
                            var id = parseInt( $('.selected').attr('id'))+1;
                            if(id>4 && $('#9').html() != data.page.totalPageCount){
                                for (var i = 1; i < 10; i++) {
                                    var text = $('#'+i).html();
                                    $('#'+i).html(parseInt(text)+1);
                                }
                            }else{
                                $('.selected').removeClass('selected');
                                $('#'+id).addClass('selected');
                            }
                            showPage(parseInt(pageNow) +1);
                        }
                    });
                    for(var i=1;i<5;i++){
                        $('#'+i).click(function(event) {
                            $('.selected').removeClass('selected');
                            $(this).addClass('selected');
                            showPage($(this).html());
                        });
                    }
                    for(var i = 5 ;i<10;i++){
                        $('#'+i).click(function(event) {
                            var temp = $(this).html();
                            if($('.selected').attr('id')>$(this).attr('id')){
                                $('.selected').removeClass('selected');
                                $(this).addClass('selected');
                            }
                            else if($('#9').html() != data.page.totalPageCount && $(this).html() != pageNow){
                                for (var i = 1; i < 10; i++) {
                                var text = $('#'+i).html();
                               $('#'+i).html(parseInt(text)+1);
                            }
                            $('.selected').removeClass('selected');
                            $('#'+(parseInt($(this).attr('id'))-1)).addClass('selected');
                            }else{
                                $('.selected').removeClass('selected');
                                $(this).addClass('selected');
                            }
                        showPage(temp);
                    });
                    }

                    init = true;
                    }


                    //show table
                    $('.form-view').slideDown('slow', function() {
                        //show pages
                        $('.page-view').slideDown('400');
                    });

                    
                    $(function() {
                        $('.modify-nav').click(function() {
                            $('#submit').unbind('click', addHandler);
                            $('#submit').unbind('click',modifyHandler);
                            $('#submit').bind('click',modifyHandler);
                            billId = $(this).attr('billId');
                            toggleNavigation();
                            $.ajax({
                                url: '../bill/selectById',
                                type: 'POST',
                                dataType: 'json',
                                data: {"billId": $(this).attr('billId')},
                            })
                            .done(function(data) {
                                var type = "";
                                if(data.billtype == 'O') type="支出";
                                else type = "收入";
                                /*$('#Select1').val(data.billtype);
                                $('#Select2').val(data.billFlag_1);
                                $('#Select3').val(data.billFlag_2);*/
                                $('#money').val(data.billMoney);
                                $('#date').val(data.billDate);
                                $('#note').val(data.billNote);
                            });
                        })
                    });

                    $('.delete').click(function(event) {
                        $.ajax({
                            url: '../bill/delBill',
                            type: 'POST',
                            dataType: 'text',
                            data: {"billId": $(this).attr('billId')},
                        })
                        .done(function(data) {
                            if(data == 'true') {
                                var current = pageNow;
                                pageNow = 0;
                                showPage(current);
                            }
                        });
                        
                    });
                }).fail(function() {
        }).always(function(xhr) {
        if(xhr.status == 302){
            window.location.href = '../user/geUserInfo';
        }
         });
        });
    })
}

//add bill handler
function addHandler(event) {
    var date = $('#date').val();
    var arr = new Array();
    arr = date.split('/');
    date = arr[2]+'/'+arr[0]+'/'+arr[1];
    $.ajax({
        url: '../bill/addBill',
        type: 'POST',
        dataType: 'text',
        data: { 'billType': $('#Select1').val(),
                'billFlag_1':$('#Select2').val(),
                'billFlag_2':$('#Select3').val(),
                'billMoney':$('#money').val(),
                'billDate':date,
                'billNote':$('#note').val()},
    })
    .done(function(data) {
        if(data == 'true') {
            $('#money').val('');
            $('#date').val('');
            toggleNavigation();
            var current = pageNow;
            pageNow = 0;
            showPage(current);
        }
    })
    .fail(function() {
    })
    .always(function(xhr) {
        if(xhr.status == 302){
            window.location.href = '../user/geUserInfo';
        }
    });
}
//modify bill handler
function modifyHandler(event){
    var date = $('#date').val();
    var arr = new Array();
    arr = date.split('/');
    date = arr[2]+'/'+arr[0]+'/'+arr[1];
    $.ajax({
        url: '../bill/modifyBill',
        type: 'POST',
        dataType: 'text',
        data: { 'billType': $('#Select1').val(),
                'billFlag_1':$('#Select2').val(),
                'billFlag_2':$('#Select3').val(),
                'billMoney':$('#money').val(),
                'billDate':date,
                'billId':billId,
                'billNote':$('#note').val()},
    })
    .done(function(data) {
        if(data == 'true') {
            $('#money').val('');
            $('#date').val('');
            toggleNavigation();
            var current = pageNow;
            pageNow = 0;
            showPage(current);
        }
    })
    .fail(function() {
    })
    .always(function(xhr) {
        if(xhr.status == 302){
            window.location.href = '../user/geUserInfo';
        }
    });
}
