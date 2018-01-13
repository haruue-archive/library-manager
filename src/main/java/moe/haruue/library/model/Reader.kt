package moe.haruue.library.model

/**
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */
data class Reader(
        var userId: Int,
        var userName: String? = null,
        var userEmail: String? = null,
        var userTel: String? = null
)