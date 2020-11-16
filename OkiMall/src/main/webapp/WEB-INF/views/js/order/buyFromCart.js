$(document).ready(function(){

    // 가격 업데이트
    updatePrice();

    // 전체 선택 체크박스
    $("#checkAll").on("click", function(){
        $(".check").prop('checked',this.checked);
    });

    // 체크박스 중 선택 안된거 존재시 전체선택 해제
    $(".check").on("click", function(){
        $("#checkAll").prop('checked', false);
    });

    // 선택상품 삭제버튼 클릭시
    $("#btn_delete_check").on("click",function(){
        // 체크여부 유효성검사
        if($("input[name='check']:checked").length==0){
            alert("삭제할 상품을 선택해주세요.");
            return;
        }
        //체크된 상품이 존재할 경우
        var result = confirm("선택한 상품을 삭제하시겠습니까?");
        if(result){
            $("input[name='check']:checked").each(function(i){
                var gds_no = $(this).val();
                $("#productVO_"+gds_no).remove();
                updatePrice();
            });

            if($("#ordertb> tbody tr").length==0){
                $("#thead").append("<tr><td colspan='7' style='padding:20px 0px;'><span>구매할 상품이 존재하지 않습니다.</span></td></tr>");                
            }
        }else{}

    });

    // 결제하기 버튼 클릭시
    $("#btn_submit").on("click",function(){
        //주문상품이 없는 경우
        if($("#ordertb >tbody tr").length==0){
            alert("결제할 상품이 없습니다.\n메인화면으로 돌아갑니다.");
            location.href="/";
            return;
        }else{
            //존재하는 경우
            $("#orderForm").submit();
        }
    });

});

var updatePrice = function(){
    var totalPrice = 0;
    var totalDiscount = 0;

    $(".productRow").each(function(index,item){
        var price = $(this).find("input[name='price']").val();
        var discount = $(this).find("input[name='discount']").val();
        var amount = $(this).find("input[name='amount']").val();

        totalPrice += price * amount;
        totalDiscount += (price-discount)*amount;
    });

    $("#totalPrice").html(numberFormat(totalPrice)+"원");
    $("#totalDiscount").html(numberFormat(totalDiscount)+"원");
    $("#ord_total_price").val(totalDiscount);
};

//숫자 콤마 설정
function numberFormat(inputNumber) {
    return inputNumber.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}