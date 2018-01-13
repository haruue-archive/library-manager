package moe.haruue.library.service

import moe.haruue.library.model.Book

/**
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */
interface BookService {

    fun list(pageNumber: Int, pageSize: Int): List<Book>

    fun query(search: String, pageNumber: Int, pageSize: Int): List<Book>

    fun add(book: Book): Int

    fun modify(book: Book): Int

    fun delete(bookId: Int): Int

    fun get(bookId: Int): Book?

}
