package moe.haruue.library.service

import moe.haruue.library.model.Note

/**
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */
interface NoteService {

    fun list(pageNumber: Int, pageSize: Int): List<Note>
    fun count(): Int

    fun queryByUserId(userId: Int, pageNumber: Int, pageSize: Int): List<Note>
    fun countQueryByUserId(userId: Int): Int

    fun queryByBookId(bookId: Int, pageNumber: Int, pageSize: Int): List<Note>
    fun countQueryByBookId(bookId: Int): Int

    fun get(noteId: Int): Note?

    fun add(note: Note): Int

    fun modify(note: Note): Int

}