// 버튼 클릭 이벤트
$(document).ready(function(){
    
    //전체선택 체크박스 클릭시
    $("#checkAll").on("click",function(){
        $(".check").prop('checked', this.checked);
    });

    // 체크박스 중 선택안된 체크박스 존재시 전체선택 해제
    $(".check").on("click",function(){
        $("#checkAll").prop('checked', false);
    });

    // 선택 상품 수정버튼 클릭시
    $("#btn_edit_check").on("click",function(){

        //체크여부 유효성 검사
        if($("input[name='check']:checked").length==0){
            alert("수정할 상품을 선택해주세요");
            return;
        }

        var checkArr = [];
        var oddArr = [];
        var buyArr = [];

        var gds_odd = $("#gds_odd").val();
        var gds_buytf = $("#gds_buytf:selected").val();

        //체크된 상품의 값을 가져옴
        $("input[name='check']:checked").each(function(i){
            var gds_no = $(this).val();
            var gds_odd = $("input[name='amount_" + gds_no + "']").val();
            var gds_buytf = $("select[name='buy_" + gds_no + "']").val();

            checkArr.push(gds_no);
            oddArr.push(gds_odd);
            buyArr.push(gds_buytf);
        });

        $.ajax({
            url : '/admin/product/editChecked',
            type : 'post',
            dataType : 'text',
            data : {
                checkArr : checkArr,
                oddArr : oddArr,
                buyArr : buyArr
                },
            success : function(data){
                alert("수정이 완료되었습니다.");
                location.href = "/admin/product/list${pm.cri.makeSearch(pm.cri.page)}";
            }
        });

    });

    // 선택 상품 삭제 버튼 클릭시
    $("#btn_delete_check").on("click",function(){

         //체크여부 유효성 검사
         if($("input[name='check']:checked").length==0){
            alert("삭제할 상품을 선택해주세요");
            return;
        }

        //체크 된 상품이 존재할 경우 진행
        var result = confirm("선택한 상품을 삭제하시겠습니까?");
        if(result){
            var checkArr = [];
            var imgArr = [];

            //체크된 상품의 값을 가져옴
            $("input[name='check']:checked").each(function(i){
                var gds_no = $(this).val();
                var gds_imag = $("input[name='img_"+gds_no+"']").val();

                checkArr.push(gds_no);
                imgArr.push(gds_imag);
            });

            $.ajax({
                url : '/admin/product/deleteChecked',
                type : 'post',
                dataType : 'text',
                data : {
                    checkArr : checkArr,
                    imgArr : imgArr
                },
                success : function(data){
                    alert("삭제가 완료되었습니다.");
                    location.href = "/admin/product/list${pm.cri.makeSearch(pm.cri.page)}";
                }
            });
        }else{}
    });

    //상품리스트 삭제버튼 클릭시
    $("button[name=btn_delete").on("click", function(){
        
        var result = confirm("이 상품을 삭제하시겠습니까?");

        if(result){
            $(this).parent().submit();
        }else{}

    });

    // 상품수정 버튼 클릭시
    var clickEdit = function(gds_no){
        var url = '/admin/product/edit${pm.makeSearch(pm.cri.page)&gds_no=}' + gds_no; 
        location.href = url;
    };

});