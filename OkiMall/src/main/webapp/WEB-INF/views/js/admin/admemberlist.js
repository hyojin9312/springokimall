$(document).ready(function(){

    $("#checkAll").on("click", function() {
		$(".check").prop('checked', this.checked);
    });

    $(".check").on("click", function() {
		$("#checkAll").prop('checked', false);
    });

});