package moe.haruue.library.mapper

import moe.haruue.library.model.Reader
import org.apache.ibatis.annotations.Param

/**
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */
interface ReaderMapper {
    fun findOne(@Param("userId") userId: Int): Reader?
    fun findAll(@Param("index") index: Int, @Param("pageSize") pageSize: Int): List<Reader>
    fun countAll(): Int
    fun query(@Param("search") search: String, @Param("index") index: Int, @Param("pageSize") pageSize: Int): List<Reader>
    fun countQuery(@Param("search") search: String): Int
    fun insertOne(reader: Reader): Int
    fun updateOne(reader: Reader): Int
    fun deleteOne(@Param("userId") userId: Int): Int
}