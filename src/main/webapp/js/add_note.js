$(document).ready(function () {
    
    var setNoteInfo = function (note) {
        $("#noteId").val(note.noteId);
        $("#userId").val(note.userId);
        $("#userName").val(note.userName);
        $("#bookId").val(note.bookId);
        $("#bookName").val(note.bookName);
        $("#noteDate").val(note.noteDate);
        $("#noteDeadline").val(note.noteDeadline);
        $("#noteStatus").val(note.noteStatus);
    };

    var getNoteInfo = function () {
        return $("form").serialize();
    };

    var getUrlParams = function () {
        var params = {};
        location.search.substring(1)
            .split("&")
            .forEach(function (i) {
                var p = i.split("=");
                params[p[0]] = p[1];
            });
        return params;
    };

    var doUpdate = function () {
        var data = getNoteInfo();
        $("input").attr("disabled", true);
        $.ajax({
            url: "api/note/modify",
            data: data,
            type: "post",
            success: function (result) {
                $("input").attr("disabled", false);
                if (result.status !== 0) {
                    alert(result.message);
                    return;
                }
                alert("借阅记录更新成功！");
                window.history.back();
            },
            error: function () {
                $("input").attr("disabled", false);
                alert("借阅记录更新失败，请稍后重试。")
            }
        });
    };

    var doAdd = function () {
        var data = getNoteInfo();
        $("input").attr("disabled", true);
        $.ajax({
            url: "api/note/add",
            data: data,
            type: "post",
            success: function (result) {
                $("input").attr("disabled", false);
                if (result.status !== 0) {
                    alert(result.message);
                    return;
                }
                alert("借阅记录添加成功！");
                window.history.back();
            },
            error: function () {
                $("input").attr("disabled", false);
                alert("借阅记录添加失败，请稍后重试。")
            }
        });
    };

    var updateMode = function (noteId) {
        console.log("update: " + noteId);
        $.ajax({
            url: "api/note/get",
            data: "noteId=" + noteId,
            type: "post",
            success: function (result) {
                if (result.status !== 0) {
                    // error occurred, fallback to add mode
                    window.location.href = "add_book.html";
                    return;
                }
                setNoteInfo(result.data);
                // set button to update
                var submitButton = $("#button-submit");
                submitButton.val("修改");
                submitButton.click(function () {
                    doUpdate();
                });
                $("input").attr("disabled", false);
            },
            error: function () {
                // error occurred, fallback to add mode
                window.location.href = "add_book.html";
            }
        })
    };

    var addMode = function () {
        console.log("add");
        // set button to add
        var submitButton = $("#button-submit");
        submitButton.val("添加");
        submitButton.click(function () {
            doAdd();
        });
        // get param and set default value
        var params = getUrlParams();
        for (var p in params) {
            console.log(p + ": " + params[p]);
            if (p === "userId") {
                var userIdInput = $("#userId");
                userIdInput.val(params["userId"]);
                userIdInput.attr("readonly", true);
            } else if (p === "bookId") {
                var bookIdInput = $("#bookId");
                bookIdInput.val(params["bookId"]);
                bookIdInput.attr("readonly", true);
            }
        }
        $("input").attr("disabled", false);
    };
    
    var init = function () {
        // disable everything before it loaded
        $("input").attr("disabled", true);
        var noteId = window.location.hash.substring(1);
        if (noteId) {
            // update mode, load note data
            updateMode(noteId);
        } else {
            // add mode
            addMode();
        }
    };

    init();
});
