package moe.haruue.library.service.impl

import moe.haruue.library.mapper.NoteMapper
import moe.haruue.library.model.Note
import moe.haruue.library.service.NoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */
@Service
class NoteServiceImpl : NoteService {

    @Suppress("SpringKotlinAutowiring")
    @Autowired
    lateinit var mapper: NoteMapper

    override fun queryByUserId(userId: Int, pageNumber: Int, pageSize: Int): List<Note> {
        return mapper.queryByUserId(userId, pageNumber * pageSize, pageSize)
    }

    override fun countQueryByUserId(userId: Int): Int {
        return mapper.countQueryByUserId(userId)
    }

    override fun queryByBookId(bookId: Int, pageNumber: Int, pageSize: Int): List<Note> {
        return mapper.queryByBookId(bookId, pageNumber * pageSize, pageSize)
    }

    override fun countQueryByBookId(bookId: Int): Int {
        return mapper.countQueryByBookId(bookId)
    }

    override fun get(noteId: Int): Note? {
        return mapper.findOne(noteId)
    }

    override fun add(note: Note): Int {
        return mapper.insertOne(note)
    }

    override fun modify(note: Note): Int {
        return mapper.updateOne(note)
    }

}