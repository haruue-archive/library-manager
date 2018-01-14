$(document).ready(function () {

    var currentQueryMethod = "queryByUserId";
    var currentIdType = "userId";
    var currentId = 0;

    var editNote = function (noteId) {
        window.location.href = "add_note.html#" + noteId;
    };

    var buildTable = function (result) {
        var notesTable = $(".book_list tbody");
        notesTable.empty();
        // table header
        var th = $("<tr class='title'></tr>")
            .append("<th>读者证号</th>")
            .append("<th>图书编号</th>")
            .append("<th>借阅日期</th>")
            .append("<th>归还期限</th>")
            .append("<th style='border:none;'></th>");
        notesTable.append(th);
        // table content
        var notes = result.data.list;
        $.each(notes, function (index, item) {
            var userId = $("<td></td>").append(item.userId);
            var bookId = $("<td></td>").append(item.bookId);
            var noteDate = $("<td></td>").append(item.noteDate);
            var noteDeadline = $("<td></td>").append(item.noteDeadline);
            var modifyButton = $("<button class='modified'></button>").append("修 改");
            modifyButton.click(function () {
                editNote(item.noteId);
            });
            var modifyButtonPanel = $("<td style='border: none;'></td>").append(modifyButton);
            $("<tr class='containt'></tr>")
                .append(userId)
                .append(bookId)
                .append(noteDate)
                .append(noteDeadline)
                .append(modifyButtonPanel)
                .appendTo(notesTable)
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
            url: "api/note/" + currentQueryMethod,
            data: currentIdType + "=" + currentId +
                  "&pageNumber=" + (pageNumber - 1),
            type: "post",
            success: function (result) {
                buildTable(result);
                buildPageInfo(result);
                buildPageNav(result);
            }
        })
    };

    var recentMode = function () {
        currentQueryMethod = "list";
        toPage(1);
    };

    var bookMode = function (bookId) {
        currentQueryMethod = "queryByBookId";
        currentIdType = "bookId";
        currentId = bookId;
        toPage(1)
    };

    var userMode = function (userId) {
        currentQueryMethod = "queryByUserId";
        currentIdType = "userId";
        currentId = userId;
        toPage(1)
    };

    var init = function () {
        if (window.location.search) {
            if (window.location.search.match("userId")) {
                var userId = window.location.search.split("=")[1];
                userMode(userId);
            } else if (window.location.search.match("bookId")) {
                var bookId = window.location.search.split("=")[1];
                bookMode(bookId);
            } else {
                recentMode()
            }
        } else {
            recentMode()
        }
    };

    init();
});
