package moe.haruue.library.model

import java.sql.Date

/**
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */
data class Book(
        var bookId: Int = 0,
        var bookName: String? = null,
        var isbn: String? = null,
        var bookType: String? = null,
        var bookPublishDate: Date? = null,
        var bookPublishCompany: String? = null,
        var bookBuyDate: Date? = null,
        var bookStatus: String? = null
) {
    companion object {
        const val STATUS_AVAILABLE = 1
        const val STATUS_UNAVAILABLE = 0
    }
}