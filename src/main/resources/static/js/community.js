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