package moe.haruue.library.controller

import moe.haruue.library.model.*
import moe.haruue.library.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

/**
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */
@Controller
@RequestMapping("api/book")
class BookController {

    @Autowired
    lateinit var service: BookService

    @RequestMapping("list")
    @ResponseBody
    fun list(
            @RequestParam(value = "pageNumber", defaultValue = "0") pageNumber: Int,
            @RequestParam(value = "pageSize", defaultValue = "10") pageSize: Int
    ): ResultWrapper<ListWrapper<Book>> {
        val r = service.list(pageNumber, pageSize)
        val c = service.count()
        return ResultWrapper(STATUS_SUCCESS, MESSAGE_SUCCESS, ListWrapper(
                list = r,
                currentPage = pageNumber,
                pageSize = pageSize,
                itemCount = c))
    }

    @RequestMapping("query")
    @ResponseBody
    fun query(
            @RequestParam("search") search: String?,
            @RequestParam(value = "pageNumber", defaultValue = "0") pageNumber: Int,
            @RequestParam(value = "pageSize", defaultValue = "10") pageSize: Int
    ): ResultWrapper<ListWrapper<Book>> {
        if (search.isNullOrBlank()) {
            // fallback to list if no search words
            return list(pageNumber, pageSize)
        }
        val r = service.query(search!!, pageNumber, pageSize)
        val c = service.countQuery(search)
        return ResultWrapper(STATUS_SUCCESS, MESSAGE_SUCCESS, ListWrapper(
                list = r,
                currentPage = pageNumber,
                pageSize = pageSize,
                itemCount = c))
    }

    @RequestMapping("get")
    @ResponseBody
    fun get(@RequestParam("bookId") bookId: Int): ResultWrapper<Book> {
        val r = service.get(bookId) ?: return ResultWrapper(1, "没有找到这本书")
        return ResultWrapper(STATUS_SUCCESS, MESSAGE_SUCCESS, r)
    }

    @RequestMapping("add")
    @ResponseBody
    fun add(book: Book?): ResultWrapper<Nothing?> {
        if (book == null) {
            return ResultWrapper(2, "缺少参数")
        }
        val r = service.add(book)
        if (r == 1) {
            return ResultWrapper(STATUS_SUCCESS, MESSAGE_SUCCESS)
        } else {
            return ResultWrapper(1, "发生错误")
        }
    }

    @RequestMapping("modify")
    @ResponseBody
    fun modify(book: Book?): ResultWrapper<Nothing?> {
        if (book == null) {
            return ResultWrapper(2, "缺少参数")
        }
        println("modify: $book")
        val r = service.modify(book)
        println("error: $r")
        if (r == 1) {
            return ResultWrapper(STATUS_SUCCESS, MESSAGE_SUCCESS)
        } else {
            return ResultWrapper(1, "发生错误")
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    fun delete(@RequestParam("bookId") bookId: Int): ResultWrapper<Nothing?> {
        val r = service.delete(bookId)
        if (r == 1) {
            return ResultWrapper(STATUS_SUCCESS, MESSAGE_SUCCESS)
        } else {
            return ResultWrapper(1, "发生错误")
        }
    }

}