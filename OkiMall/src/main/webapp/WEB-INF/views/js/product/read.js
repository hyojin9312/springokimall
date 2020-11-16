var replyPage = 1;

$(document).ready(function(){

    // 장바구니
    $("#btn_cart").on("click", function(){
        var gds_no = $("#gds_no").val();
        var gds_odd = $("#ord_amount").val();

        $.ajax({
            url:"/cart/addMany",
            type: "post",
            dataType: "text",
            data: {
                gds_no : gds_no,
                gds_odd : gds_odd
            },
            success: function(data){
                var result = confirm("장바구니에 추가되었습니다.\n지금 확인하시겠습니까?");
                if(result){
                    location.href="/cart/list";
                }else{}
            }
        });
    });

    // 별점 클릭시 색상 변경
    $("#star_grade a").click(function(){
        $(this).parent().children("a").removeClass("on");
        $(this).addClass("on").prevAll("a").addClass("on");
            return false;
    });

    // 상품후기 쓰기 버튼 클릭시
    $("#btn_write_review").on("click",function(){
        
        var rv_grade = 0;
        // 후기내용
        var rv_conte = $("#reviewContent").val();
        //상품코드
        var gds_no = $("#gds_no").val();

        // 선택된 별점 개수 가져옴
        $("#star_grade a").each(function(i, e){
            if($(this).attr('class')=='on'){
                rv_grade += 1;
            }
        });

        // 유효성검사
        if(rv_grade == 0){
            alert("별점을 선택해주세요.");
            return;

        }else if(rv_conte=="" || rv_conte == null){
            alert("후기 내용을 입력해주세요.");
            return;
        }

        // DB작업
        $.ajax({
            url: '/review/write',
            type:'post',
            dataType:'text',
            data: {
                "rv_grade" : rv_grade,
                "rv_conte" : rv_conte,
                "gds_no" : gds_no
            },
            success : function(data){
                alert("상품후기가 등록되었습니다.");
                $("#star_grade a").parent().children("a").removeClass("on");
                $("#reviewContent").val("");
                replyPage = 1;
                getPage("/review/" + gds_no + "/1");
            }
        });

    });

    //상품후기 보기 클릭시
    $("#repliesDiv").on("click",function(){

        var gds_no = $("#gds_no").val();

        if($(".timeline li").length > 2){
            $(".replyLi").remove();
			$(".noReview").hide();
            $(".pagination li").remove();
			return;
        }
        getPage("/review/" + gds_no + "/1");
    });

    //상품후기 하단페이지
    $(".pagination").on("click", "li a", function(event){

        var gds_no = $("#gds_no").val();

        event.preventDefault();

        replyPage = $(this).attr("href");
        getPage("/review/" + gds_no + "/" + replyPage);
    });

    $(".timeline").on("click", ".replyLi", function(event){
        alert();
        var reply = $(this);
        var rv_no = $(this).attr("data-rv_no");
        var grade = $(this).find(".rv_grade").text();

        $("#star_grade_modal a").each(function(index, item){
            if(index<grade){
                $(item).addClass('on');
            }else{
                $(item).removeClass('on');
            }
        });

        //후기내용가져오기
        $("#replytext").val(reply.find('#rv_conte').text());

        //후기번호 가져오기
        $(".modal-body").attr("data-rv_no",rv_no);

    });

    $("#star_grade_modal a").click(function(){
        $(this).parent().children("a").removeClass("on");
        $(this).addClass("on").prevAll("a").addClass("on");
            return false;
    });

    // 수정버튼 클릭시
    $("#btn_modal_modify").on("click", function(){

        var rv_no =  $(".modal-body").attr("data-rv_no");
        var rv_conte = $("#replytext").val();
        var gds_no = $("#gds_no").val();

        var rv_grade = 0;
        $("#star_grade_modal a").each(function(i, e){
            if($(this).attr('class')=='on'){
                rv_grade += 1;
            }
        });

        //DB작업
        $.ajax({
            url:'/review/modify',
            type:'post',
            data:{
                "rv_no" : rv_no,
                "rv_conte" : rv_conte,
                "rv_grade" : rv_grade
            },
            dataType: 'text',
            success : function(result){
                alert("수정 되었습니다.");
                getPage("/review/" + gds_no + "/" + replyPage);
            }
        });
    });


});

function getPage(pageInfo) {
  
	$.getJSON(pageInfo, function(data) {
		
		// 상품후기 존재
		if(data.list.length>0){
			
			printData(data.list, $("#repliesDiv"), $('#template'));
			printPaging(data.pageMaker, $(".pagination"));
	
			$("#modifyModal").modal('3');
			$("#replycntSmall").html("[ " + data.pageMaker.totalCount + " ]");
			
		// 상품후기가 존재하지 않음
		} else{
			printNoData();
			$("#replycntSmall").html("[ 0 ]");
			
		}
	});
}

// 상품후기 리스트가 존재하지 않을때 보여줌
var printNoData = function() {
	$(".replyLi").remove();
	$(".noReview").show();
}

// 상품후기 리스트보여주는 템플릿 적용
var printData = function(replyArr, target, templateObject){
    var template = Handlebars.compile(templateObject.html());

    var html = template(replyArr);
    $(".replyLi").remove();
    $(".noReview").hide();
    target.after(html);
}

var printPaging = function(pageMaker, target){
    var str = "";

    //이전
    if(pageMaker.prev){
        str += "<li><a href='" + (pageMaker.startPage-1)
               +"'> << </a></li>"; 
    }
    //페이지인덱스
    for(var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++){
        var strClass = pageMaker.cri.page == i? 'class=active' : '';
        str += "<li><a href='" + (pageMaker.endPage + 1)
            + "'> >> </a></li>";
    }
    //다음
    if(pageMaker.next){
        str += "<li><a href='" + (pageMaker.endPage + 1)
            + "'> >> </a></li>";
    }

    target.html(str);
};

// 상품후기 삭제 버튼클릭시
var deleteReview = function(rv_no){

    var result = confirm("이 상품 후기를 삭제하시겠습니까?");
    if(result){
        $.ajax({
            url: "/review/" + rv_no,
            type: "delete",
            dataType: "text",
            data:{"rv_no":rv_no},
            success: function(data){
                alert("해당 상품후기가 삭제 되었습니다.");
                var gds_no = $("#gds_no").val();
                getPage("/review/" + gds_no + "/" +replyPage);
            }
        });
    } else{}

}