package moe.haruue.library.mapper

import moe.haruue.library.model.Note
import org.apache.ibatis.annotations.Param

/**
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */
interface NoteMapper {

    fun findOne(@Param("noteId") noteId: Int): Note?

    fun list(@Param("index") index: Int,
             @Param("pageSize") pageSize: Int): List<Note>
    fun count(): Int

    fun queryByUserId(@Param("userId") userId: Int,
                      @Param("index") index: Int,
                      @Param("pageSize") pageSize: Int): List<Note>
    fun countQueryByUserId(@Param("userId") userId: Int): Int

    fun queryByBookId(@Param("bookId") bookId: Int,
                      @Param("index") index: Int,
                      @Param("pageSize") pageSize: Int): List<Note>
    fun countQueryByBookId(@Param("bookId") bookId: Int): Int

    fun insertOne(note: Note): Int
    fun updateOne(note: Note): Int
}