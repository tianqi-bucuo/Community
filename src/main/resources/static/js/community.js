function incLikeCount(cid){
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            id: cid
        }),
        dataType: "json"
    });
    $('#'+cid).text(parseInt($('#'+cid).text())+1)
}

function submit() {
    var content = $("#comment_content").val();
    if (!content) {
        alert("啥都没写你回复个der ???");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            articleId: $("#article_id").val(),
            content: content,
            userId: $("#user_id").val()
        }),
        dataType: "json"
    });
    window.location.reload();
}

function showSelectTag() {
    $("#select-tag").show();
}

function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ' ' + value);
        } else {
            $("#tag").val(value);
        }
    }
}