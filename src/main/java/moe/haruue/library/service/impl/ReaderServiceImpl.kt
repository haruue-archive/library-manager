package moe.haruue.library.service.impl

import moe.haruue.library.mapper.ReaderMapper
import moe.haruue.library.model.Reader
import moe.haruue.library.service.ReaderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */
@Service
class ReaderServiceImpl : ReaderService {

    @Suppress("SpringKotlinAutowiring")
    @Autowired
    lateinit var mapper: ReaderMapper

    override fun list(pageNumber: Int, pageSize: Int): List<Reader> {
        return mapper.findAll(pageNumber * pageSize, pageSize)
    }

    override fun count(): Int {
        return mapper.countAll()
    }

    override fun query(search: String, pageNumber: Int, pageSize: Int): List<Reader> {
        return mapper.query("%$search%", pageNumber * pageSize, pageSize)
    }

    override fun countQuery(search: String): Int {
        return countQuery("%$search%")
    }

    override fun add(reader: Reader): Int {
        return mapper.insertOne(reader)
    }

    override fun modify(reader: Reader): Int {
        return mapper.updateOne(reader)
    }

    override fun delete(userId: Int): Int {
        return mapper.deleteOne(userId)
    }

    override fun get(userId: Int): Reader? {
        return mapper.findOne(userId)
    }
}