package moe.haruue.library.model

import java.sql.Date

/**
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */
data class Note(
    var noteId: Int = 0,
    var userId: Int = 0,
    var bookId: Int = 0,
    var noteDate: Date? = null,
    var noteDeadline: Date? = null
)