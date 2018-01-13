package moe.haruue.library.controller

import moe.haruue.library.model.*
import moe.haruue.library.service.ReaderService
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
@RequestMapping("api/reader")
class ReaderController {

    @Autowired
    lateinit var service: ReaderService

    @RequestMapping("list")
    @ResponseBody
    fun list(
            @RequestParam(value = "pageNumber", defaultValue = "0") pageNumber: Int,
            @RequestParam(value = "pageSize", defaultValue = "10") pageSize: Int
    ): ResultWrapper<ListWrapper<Reader>> {
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
    ): ResultWrapper<ListWrapper<Reader>> {
        if (search.isNullOrBlank()) {
            // fallback to list if search is empty
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
    fun get(@RequestParam("userId") userId: Int): ResultWrapper<Reader> {
        val r = service.get(userId) ?: return ResultWrapper(1, "此读者不存在")
        return ResultWrapper(STATUS_SUCCESS, MESSAGE_SUCCESS, r)
    }

    @RequestMapping("add")
    @ResponseBody
    fun add(reader: Reader?): ResultWrapper<Nothing?> {
        if (reader == null) {
            return ResultWrapper(2, "缺少参数")
        }
        val r = service.add(reader)
        if (r == 1) {
            return ResultWrapper(STATUS_SUCCESS, MESSAGE_SUCCESS)
        } else {
            return ResultWrapper(1, "发生错误")
        }
    }

    @RequestMapping("modify")
    @ResponseBody
    fun modify(reader: Reader?): ResultWrapper<Nothing?> {
        if (reader == null) {
            return ResultWrapper(2, "缺少参数")
        }
        val r = service.modify(reader)
        if (r == 1) {
            return ResultWrapper(STATUS_SUCCESS, MESSAGE_SUCCESS)
        } else {
            return ResultWrapper(1, "发生错误")
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    fun delete(@RequestParam("userId") userId: Int): ResultWrapper<Nothing?> {
        val r = service.delete(userId)
        if (r == 1) {
            return ResultWrapper(STATUS_SUCCESS, MESSAGE_SUCCESS)
        } else {
            return ResultWrapper(1, "发生错误")
        }
    }

}