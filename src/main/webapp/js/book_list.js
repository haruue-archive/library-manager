$(document).ready(function () {

    var currentSearchWords = "";

    var editBook = function (bookId) {
        window.location.href = "add_book.html#" + bookId;
    };

    var deleteBook = function (bookId, bookName) {
        if (confirm("确认要删除 “" + bookName + "” 吗？\n此操作不可撤销！")) {
            $.ajax({
                url: "api/book/delete",
                data: "bookId=" + bookId,
                type: "post",
                success: function (result) {
                    if (result.status !== 0) {
                        alert(result.message);
                        return;
                    }
                    alert("删除成功！");
                    toPage(1)
                },
                error: function () {
                    alert("无法删除，可能是这本书尚未归还。")
                }
            })
        }
    };

    var buildTable = function (result) {
        var booksTable = $(".book_list tbody");
        booksTable.empty();
        var books = result.data.list;
        $.each(books, function (index, item) {
            var bookId = $("<td></td>").append(item.bookId);
            var bookName = $("<td></td>").append(item.bookName);
            var bookType = $("<td></td>").append(item.bookType);
            var bookPublishCompany = $("<td></td>").append(item.bookPublishCompany);
            var bookPublishDate = $("<td></td>").append(item.bookPublishDate);
            var bookBuyDate = $("<td></td>").append(item.bookBuyDate);
            var modifyButton = $("<button class='modified'></button>").append("修 改");
            modifyButton.click(function () {
                editBook(item.bookId);
            });
            var modifyButtonPanel = $("<td style='border: none;'></td>").append(modifyButton);
            var deleteButton = $("<button class='delete'></button>").append("删 除");
            deleteButton.click(function () {
                deleteBook(item.bookId, item.bookName);
            });
            var deleteButtonPanel = $("<td style='border: none;'></td>").append(deleteButton);
            $("<tr class='containt'></tr>")
                .append(bookId)
                .append(bookName)
                .append(bookType)
                .append(bookPublishCompany)
                .append(bookPublishDate)
                .append(bookBuyDate)
                .append(modifyButtonPanel)
                .append(deleteButtonPanel)
                .appendTo(booksTable)
        })
    };

    var buildPageInfo = function (result) {
        var pageInfoArea = $("#page_info_area");
        var d = result.data;
        pageInfoArea.empty();
        pageInfoArea.append("当前第" + (d.currentPage + 1) + "页，" +
                            "总共" + d.pageCount + "页，" +
                            "总共" + d.itemCount + "条数据");

    };

    var buildPageNav = function (result) {
        var d = result.data;
        $("#page_info_nav").twbsPagination({
            totalPages: d.pageCount,
            visiblePages: 5,
            startPage: d.currentPage + 1,
            first: "首页",
            prev: "上一页",
            next: "下一页",
            last: "末页",
            onPageClick: function (event, page) {
                toPage(page)
            }
        });
    };

    var toPage = function (pageNumber) {
        $.ajax({
            url: "api/book/query",
            data: "search=" + currentSearchWords + "&pageNumber=" + (pageNumber - 1),
            type: "post",
            success: function (result) {
                if (result.status !== 0) {
                    alert(result.message);
                    return;
                }
                buildTable(result);
                buildPageInfo(result);
                buildPageNav(result)
            }
        });
    };

    toPage(1);

    $("#button-search").click(function () {
        currentSearchWords = $("input.search").val();
        toPage(1)
    });

    $("#button-add").click(function () {
        window.location.href = "add_book.html";
    })

});
