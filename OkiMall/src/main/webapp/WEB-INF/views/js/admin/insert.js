$(document).ready(function(){
    // ckEditor작업
    var ckeditor_config = {
        resize_enabled : false,
        enterMode : CKEDITOR.ENTER_BR,
        shiftEnterMode : CKEDITOR.ENTER_P,
        toolbarCanCollapse : true,
        removePlugins : "elementspath",
        filebrowserUploadUrl : '/admin/product/imgUpload'
    };
    CKEDITOR.replace("gds_deta",ckeditor_config);

    $("#mainCategory").on("change", function(){
        var mainCGCode = $(this).val();
        var url = "/admin/product/subCGList/"+ mainCGCode;

        $.getJSON(url,function(data){
            subCGList(data,$("#subCategory"), $("#subCGListTemplate"))
        });
    });
});

var subCGList = function(subCGStr, target, templateObject) {

    var template = Handlebars.compile(templateObject.html());
    var options = template(subCGStr); // 템플릿에 2차카테고리 데이터가 바인딩된 소스

    // 기존 option 제거(누적방지)	
    $("#subCategory option").remove(); // 카테고리 선택에 의해 존재했던 2차카테고리 정보를 제거
    target.append(options);
}

$(document).ready(function(){

    var form = $("#registerForm");

    // 상품 등록 버튼 클릭시
    $("#btn_submit").on("click", function(){
        
        var result = confirm("상품을 등록하시겠습니까?");

        if(result){
            //유효성 검사
            var mainCategory = $("#mainCategory option:selected");
            var subCategory = $("#subCategory option:selected");
            var gds_name = $("#gds_name");
            var gds_make = $("#gds_make");
            var gds_pric = $("#gds_pric");
            var gds_disc = $("#gds_disc");
            var ckeditor = CKEDITOR.instances['gds_deta'];
            var file1 = $("#file1");
            var gds_odd = $("#gds_odd");
            var gds_buytf = $("#gds_buytf");
            var fileSize = file1.size;
            var fileFormat =  /(.*?)\.(jpg|jpeg|png|gif|bmp|pdf)$/;
            var maxSize =  5 * 1024 * 1024;

            if(mainCategory.val()==null || mainCategory.val() == "default"){
                alert("1차 카테고리를 선택해주세요");
                mainCategory.focus();
                return;

            } else if(subCategory.val()==null || subCategory.val() == "default"){
                alert("2차 카테고리를 선택해주세요");
                subCategory.focus();
                return;

            } else if(gds_name.val()==null || gds_name.val() == ""){
                alert("상품명을 입력해주세요");
                gds_name.focus();
                return;

            } else if(gds_make.val()==null || gds_make.val() == ""){
                alert("제조사를 입력해주세요");
                gds_make.focus();
                return;

            } else if(gds_pric.val()==null || gds_pric.val() == ""){
                alert("상품 가격을 입력해주세요");
                gds_pric.focus();
                return;

            } else if(gds_disc.val()==null || gds_disc.val() == ""){
                alert("할인 가격을 입력해주세요");
                gds_disc.focus();
                return;

            } else if(ckeditor.getData()==null || ckeditor.getData() == ""){
                alert("상품 상세 내용을 입력해주세요");
                ckeditor.focus();
                return;
                
            } else if(file1.val()==null || file1.val() == ""){
                alert("이미지 파일을 추가해주세요");
                file1.focus();
                return;
                
            } else if(!file1.val().match(fileFormat)){
                alert("이미지 파일만 업로드 가능합니다");
                file1.focus();
                return;
                
            } else if(fileSize > maxSize){
                alert("파일 사이즈는 5MB까지만 가능합니다");
                file1.focus();
                return;
                
            } else if(gds_odd.val()==null || gds_odd.val() == ""){
                alert("상품 수량을 입력해주세요");
                gds_odd.focus();
                return;
                
            } else{
                form.submit();
            }
        } else{}

    });

});