$(document).ready(function () {

    var currentSearchWords = "";

    var editReader = function (userId) {
        window.location.href = "add_reader.html#" + userId;
    };

    var deleteReader = function (userId, userName) {
        if (confirm("确认要删除用户 “" + userName + "” 吗？\n此操作不可撤销！")) {
            $.ajax({
                url: "api/reader/delete",
                data: "userId=" + userId,
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

    var borrowBook = function (userId) {
        window.location.href = "note_list.html?userId=" + userId;
    };

    var buildTable = function (result) {
        var readersTable = $(".book_list tbody");
        readersTable.empty();
        // table header
        var th = $("<tr class='title'></tr>")
            .append("<th>读者证号</th>")
            .append("<th>姓名</th>")
            .append("<th>邮箱</th>")
            .append("<th>电话</th>")
            .append("<th style='border:none;'></th>")
            .append("<th style='border:none;'></th>")
            .append("<th style='border:none;'></th>");
        readersTable.append(th);
        var readers = result.data.list;
        $.each(readers, function (index, item) {
            var userId = $("<td></td>").append(item.userId);
            var userName = $("<td></td>").append(item.userName);
            var userEmail = $("<td></td>").append(item.userEmail);
            var userTel = $("<td></td>").append(item.userTel);
            var modifyButton = $("<button class='modified'></button>").append("修 改");
            modifyButton.click(function () {
                editReader(item.userId);
            });
            var modifyButtonPanel = $("<td style='border: none;'></td>").append(modifyButton);
            var deleteButton = $("<button class='delete'></button>").append("删 除");
            deleteButton.click(function () {
                deleteReader(item.userId, item.userName);
            });
            var deleteButtonPanel = $("<td style='border: none;'></td>").append(deleteButton);
            var borrowButton = $("<button class='note'></button>").append("借阅信息");
            borrowButton.click(function () {
                borrowBook(item.userId);
            });
            var borrowButtonPanel = $("<td style='border: none;'></td>").append(borrowButton);
            $("<tr class='containt'></tr>")
                .append(userId)
                .append(userName)
                .append(userEmail)
                .append(userTel)
                .append(modifyButtonPanel)
                .append(deleteButtonPanel)
                .append(borrowButtonPanel)
                .appendTo(readersTable)
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
            url: "api/reader/query",
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
        window.location.href = "add_reader.html";
    })

});
