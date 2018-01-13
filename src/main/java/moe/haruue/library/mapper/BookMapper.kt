package moe.haruue.library.mapper

import moe.haruue.library.model.Book
import org.apache.ibatis.annotations.Param

/**
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */
interface BookMapper {
    fun findOne(@Param("bookId") bookId: Int): Book?
    fun findAll(@Param("index") index: Int, @Param("pageSize") pageSize: Int): List<Book>
    fun countAll(): Int
    fun query(@Param("search") search: String, @Param("index") index: Int, @Param("pageSize") pageSize: Int): List<Book>
    fun countQuery(@Param("search") search: String): Int
    fun insertOne(book: Book): Int
    fun updateOne(book: Book): Int
    fun deleteOne(@Param("bookId") bookId: Int): Int
}