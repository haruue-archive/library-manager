$(document).ready(function () {

    var setReaderInfo = function (reader) {
        $("#id").val(reader.userId);
        $("#name").val(reader.userName);
        $("#email").val(reader.userEmail);
        $("#tel").val(reader.userTel);

    };

    var getReaderInfo = function () {
        return $("form").serialize();
    };

    var doUpdate = function () {
        var data = getReaderInfo();
        $("input").attr("disabled", true);
        $.ajax({
            url: "api/reader/modify",
            data: data,
            type: "post",
            success: function (result) {
                $("input").attr("disabled", false);
                if (result.status !== 0) {
                    alert(result.message);
                    return;
                }
                alert("读者信息更新成功！");
                window.location.href = "reader_list.html";
            },
            error: function () {
                $("input").attr("disabled", false);
                alert("读者数据更新失败，请稍后重试。")
            }
        });
    };

    var doAdd = function () {
        var data = getReaderInfo();
        $("input").attr("disabled", true);
        $.ajax({
            url: "api/reader/add",
            data: data,
            type: "post",
            success: function (result) {
                $("input").attr("disabled", false);
                if (result.status !== 0) {
                    alert(result.message);
                    return;
                }
                alert("读者信息添加成功！");
                window.location.href = "reader_list.html";
            },
            error: function () {
                $("input").attr("disabled", false);
                alert("读者数据添加失败，请稍后重试。")
            }
        });
    };

    var updateMode = function (userId) {
        $.ajax({
            url: "api/reader/get",
            data: "userId=" + userId,
            type: "post",
            success: function (result) {
                if (result.status !== 0) {
                    // error occurred, fallback to add mode
                    window.location.href = "add_reader.html";
                    return;
                }
                setReaderInfo(result.data);
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
                window.location.href = "add_reader.html";
            }
        })
    };

    var addMode = function () {
        // set button to add
        var submitButton = $("#button-submit");
        submitButton.val("添加");
        submitButton.click(function () {
            doAdd();
        });
        $("input").attr("disabled", false);
    };

    var init = function () {
        // disable everything before it loaded
        $("input").attr("disabled", true);
        var userId = window.location.hash.substring(1);
        if (userId) {
            // update mode, load reader data
            updateMode(userId);
        } else {
            // add mode
            addMode();
        }
    };

    init();
});
