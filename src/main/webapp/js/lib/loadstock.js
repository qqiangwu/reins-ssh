$(function () {
	refreshStock();
    setInterval(function () {refreshStock();}, 5000);
}); 


function refreshStock(){
	$.ajax({
        url : LOADSTOCK.endpoint() + '/api/index/updateStockValue',
        type : 'post',
        data : {},
        dateType : 'json',
        success : function(data) {
        	$.each(data, function(i){
        		if (data[i].change < 0) 
        			var qushi = LOADSTOCK.urlResource() + '/images/down_arrw.png';
        		else var qushi = LOADSTOCK.urlResource() + '/images/up_arrw.png';
        		var temp = data[i]["new"]+ ' &nbsp; ' +(100*data[i].absChangePercent).toFixed(2)+'%';
        		var detailhq = LOADSTOCK.urlStock()+'/stockHistory/'+data[i].code;
        		$('#stockval'+i).html("<div class='left_arrw'><span></span><img src='"+ qushi + "' /></div>"+
        				"<div class='right_zb'><span class='namecity'>"+data[i].name
        				+"</span><span class='number_pre' onclick = \"window.open(\'"+
        				detailhq+"\')\">"+temp+"</span></div>");
        	});
        },
        error : function(data) {
            return false;
        }
    });
}
