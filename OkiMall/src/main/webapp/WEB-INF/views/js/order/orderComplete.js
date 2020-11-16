$(document).ready(function(){

    // 주문내역 확인 클릭시
    $("#btn_orderList").on("click",function(){
        location.href="/order/list";
    });

    // 계속쇼핑하기 클릭시
    $("#btn_main").on("click", function(){
        location.href="/";
    });

});