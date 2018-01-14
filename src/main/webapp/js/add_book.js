$(document).ready(function () {
    
    var setBookInfo = function (book) {
        $("#bookId").val(book.bookId);
        $("#name").val(book.bookName);
        $("#isbn").val(book.isbn);
        $("#book-type").val(book.bookType);
        $("#publish").val(book.bookPublishCompany);
        $("#pubdate").val(book.bookPublishDate);
        $("#buydate").val(book.bookBuyDate);
        $("#state").val(book.bookStatus)
    };

    var getBookInfo = function () {
        // return "bookId=" + $("#bookId").val() +
        //     "&bookName=" + encodeURIComponent($("#name").val()) +
        //     "&isbn=" + $("#isbn").val() +
        //     "&bookType=" + encodeURIComponent($("#book-type").val()) +
        //     "&bookPublishCompany=" + encodeURIComponent($("#publish").val()) +
        //     "&bookPublishDate=" + Date.parse($("#pubdate").val()) +
        //     "&bookBuyDate=" + Date.parse($("#buydate").val()) +
        //     "&bookStatus=" + $("#state").val()
        return $("form").serialize();
    };

    var doUpdate = function () {
        var data = getBookInfo();
        $("input").attr("disabled", true);
        $.ajax({
            url: "api/book/modify",
            data: data,
            type: "post",
            success: function (result) {
                $("input").attr("disabled", false);
                if (result.status !== 0) {
                    alert(result.message);
                    return;
                }
                alert("图书信息更新成功！");
                window.location.href = "book_list.html";
            },
            error: function () {
                $("input").attr("disabled", false);
                alert("图书数据更新失败，请稍后重试。")
            }
        });
    };

    var doAdd = function () {
        var data = getBookInfo();
        $("input").attr("disabled", true);
        $.ajax({
            url: "api/book/add",
            data: data,
            type: "post",
            success: function (result) {
                $("input").attr("disabled", false);
                if (result.status !== 0) {
                    alert(result.message);
                    return;
                }
                alert("图书信息添加成功！");
                window.location.href = "book_list.html";
            },
            error: function () {
                $("input").attr("disabled", false);
                alert("图书数据添加失败，请稍后重试。")
            }
        });
    };

    var updateMode = function (bookId) {
        $.ajax({
            url: "api/book/get",
            data: "bookId=" + bookId,
            type: "post",
            success: function (result) {
                if (result.status !== 0) {
                    // error occurred, fallback to add mode
                    window.location.href = "add_book.html";
                    return;
                }
                setBookInfo(result.data);
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
        var bookId = window.location.hash.substring(1);
        if (bookId) {
            // update mode, load book data
            updateMode(bookId);
        } else {
            // add mode
            addMode();
        }
    };

    init();
});
