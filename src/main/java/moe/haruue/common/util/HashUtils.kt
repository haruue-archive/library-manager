package moe.haruue.common.util

import java.math.BigInteger
import java.security.MessageDigest



/**
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

fun sha256sum(bytesOfMessage: ByteArray): String {
    val md = MessageDigest.getInstance("SHA-256")
    val thedigest = md.digest(bytesOfMessage)
    val bigInt = BigInteger(1, thedigest)
    var hashtext = bigInt.toString(16)
    // Now we need to zero pad it if you actually want the full 32 chars.
    while (hashtext.length < 32) {
        hashtext = "0" + hashtext
    }
    return hashtext
}

fun String.sha256sum(): String = sha256sum(this.toByteArray(Charsets.UTF_8))


