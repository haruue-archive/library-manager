package moe.haruue.library.controller

import moe.haruue.library.model.*
import moe.haruue.library.service.NoteService
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
@RequestMapping("api/note")
class NoteController {

    @Autowired
    lateinit var service: NoteService

    @RequestMapping("queryByUserId")
    @ResponseBody
    fun queryByUserId(
            @RequestParam("userId") userId: Int,
            @RequestParam(value = "pageNumber", defaultValue = "0") pageNumber: Int,
            @RequestParam(value = "pageSize", defaultValue = "10") pageSize: Int
    ): ResultWrapper<ListWrapper<Note>> {
        val r = service.queryByUserId(userId, pageNumber, pageSize)
        val c = service.countQueryByUserId(userId)
        return ResultWrapper(STATUS_SUCCESS, MESSAGE_SUCCESS, ListWrapper(
                list = r,
                currentPage = pageNumber,
                pageSize = pageSize,
                itemCount = c))
    }

    @RequestMapping("queryByBookId")
    @ResponseBody
    fun queryByBookId(
            @RequestParam("bookId") bookId: Int,
            @RequestParam(value = "pageNumber", defaultValue = "0") pageNumber: Int,
            @RequestParam(value = "pageSize", defaultValue = "10") pageSize: Int
    ): ResultWrapper<ListWrapper<Note>> {
        val r = service.queryByBookId(bookId, pageNumber, pageSize)
        val c = service.countQueryByBookId(bookId)
        return ResultWrapper(STATUS_SUCCESS, MESSAGE_SUCCESS, ListWrapper(
                list = r,
                currentPage = pageNumber,
                pageSize = pageSize,
                itemCount = c))
    }

    @RequestMapping("list")
    @ResponseBody
    fun list(
            @RequestParam(value = "pageNumber", defaultValue = "0") pageNumber: Int,
            @RequestParam(value = "pageSize", defaultValue = "10") pageSize: Int
    ): ResultWrapper<ListWrapper<Note>> {
        val r = service.list(pageNumber, pageSize)
        val c = service.count()
        return ResultWrapper(STATUS_SUCCESS, MESSAGE_SUCCESS, ListWrapper(
                list = r,
                currentPage = pageNumber,
                pageSize = pageSize,
                itemCount = c))
    }

    @RequestMapping("get")
    @ResponseBody
    fun get(@RequestParam("noteId") noteId: Int): ResultWrapper<Note> {
        val r = service.get(noteId) ?: return ResultWrapper(1, "没有找到这条记录")
        return ResultWrapper(STATUS_SUCCESS, MESSAGE_SUCCESS, r)
    }

    @RequestMapping("add")
    @ResponseBody
    fun add(note: Note?): ResultWrapper<Nothing?> {
        if (note == null) {
            return ResultWrapper(2, "缺少参数")
        }
        val r = service.add(note)
        if (r == 1) {
            return ResultWrapper(STATUS_SUCCESS, MESSAGE_SUCCESS)
        } else {
            return ResultWrapper(1, "发生错误")
        }
    }

    @RequestMapping("modify")
    @ResponseBody
    fun modify(note: Note?): ResultWrapper<Nothing?> {
        if (note == null) {
            return ResultWrapper(2, "缺少参数")
        }
        val r = service.modify(note)
        if (r == 1) {
            return ResultWrapper(STATUS_SUCCESS, MESSAGE_SUCCESS)
        } else {
            return ResultWrapper(1, "发生错误")
        }
    }

}