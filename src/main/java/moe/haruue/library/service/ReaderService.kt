package moe.haruue.library.service

import moe.haruue.library.model.Reader

/**
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */
interface ReaderService {

    fun list(pageNumber: Int, pageSize: Int): List<Reader>

    fun count(): Int

    fun query(search: String, pageNumber: Int, pageSize: Int): List<Reader>

    fun countQuery(search: String): Int

    fun add(reader: Reader): Int

    fun modify(reader: Reader): Int

    fun delete(userId: Int): Int

    fun get(userId: Int): Reader?

}