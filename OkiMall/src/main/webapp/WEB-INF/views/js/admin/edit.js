$(document).ready(function(){

    var form = $("#editForm");

    //상품 수정 버튼 클릭시
    $("#btn_submit").on("click", function(){
        var result = confirm("수정된 정보를 저장하시겠습니까?");

        if(result){
            var mainCategroy = $("#mainCategory option:selected");
            var subCategory = $("#subCategory option:selected");
            var gds_name = $("#gds_name");
            var gds_make = $("#gds_make");
            var gds_pric = $("#gds_pric");
            var gds_disc = $("#gds_disc");
            var ckeditor = CKEDITOR.instances['gds_deta'];
            var gds_deta = $("#gds_deta");
            var gds_odd = $("#gds_odd");
            var gds_buytf = $("#gds_buytf");

            if(mainCategroy.val()==null || mainCategroy.val()=="default"){
                alert("1차 카테고리를 선택해주세요");
                mainCategroy.focus();
                return;

            } else if(subCategory.val()==null || subCategory.val()=="default"){
                alert("2차 카테고리를 선택해주세요");
                subCategory.focus();
                return;

            } else if(gds_name.val()==null || gds_name.val() == ""){
                alert("상품명을 입력해주세요");
                gds_name.focus();
                return;

            } else if(gds_make.val()==null || gds_make.val() == ""){
                alert("상품 제조사를 입력해주세요");
                gds_make.focus();
                return;

            } else if(gds_pric.val()==null || gds_pric.val() == ""){
                alert("상품 가격을 입력해주세요");
                gds_pric.focus();
                return;

            } else if(gds_disc.val()==null || gds_disc.val() == ""){
                alert("상품 할인 가격을 입력해주세요");
                gds_disc.focus();
                return;

            } else if(ckeditor.getData()==null || ckeditor.getData() == ""){
                alert("상품 상세 내용을 입력해주세요");
                ckeditor.focus();
                return;

            } else if(gds_odd.val()==null || gds_odd.val() == ""){
                alert("상품 수량을 입력해주세요");
                gds_odd.focus();
                return;

            }else{
                form.submit();
            }

        } else{}
    });
    
});