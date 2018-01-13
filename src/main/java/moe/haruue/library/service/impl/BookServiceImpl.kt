package moe.haruue.library.service.impl

import moe.haruue.library.mapper.BookMapper
import moe.haruue.library.model.Book
import moe.haruue.library.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */
@Service
class BookServiceImpl : BookService {

    @Suppress("SpringKotlinAutowiring")
    @Autowired
    lateinit var mapper: BookMapper

    override fun list(pageNumber: Int, pageSize: Int): List<Book> {
        return mapper.findAll(pageNumber * pageSize, pageSize)
    }

    override fun count(): Int {
        return mapper.countAll()
    }

    override fun query(search: String, pageNumber: Int, pageSize: Int): List<Book> {
        return mapper.query("%$search%", pageNumber * pageSize, pageSize)
    }

    override fun countQuery(search: String): Int {
        return mapper.countQuery("%$search%")
    }

    override fun add(book: Book): Int {
        return mapper.insertOne(book)
    }

    override fun modify(book: Book): Int {
        return mapper.updateOne(book)
    }

    override fun delete(bookId: Int): Int {
        return mapper.deleteOne(bookId)
    }

    override fun get(bookId: Int): Book? {
        return mapper.findOne(bookId)
    }
}