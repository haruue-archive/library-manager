package moe.haruue.library.model

/**
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

val STATUS_SUCCESS = 0
val MESSAGE_SUCCESS = "成功"

data class ResultWrapper<out D>(
        val status: Int,
        val message: String,
        val data: D? = null
)

data class ListWrapper<out D>(
        val list: List<D>,
        val currentPage: Int,
        val pageCount: Int,
        val pageSize: Int,
        val itemCount: Int
) {
    constructor(
            list: List<D>,
            currentPage: Int,
            pageSize: Int,
            itemCount: Int
    ) : this(list, currentPage,
            Math.ceil(itemCount.toDouble() / pageSize).toInt(),
            pageSize, itemCount)
}