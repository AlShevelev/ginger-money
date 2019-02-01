package com.syleiman.gingermoney.core.helpers.id

import java.nio.ByteBuffer
import java.util.*

object IdUtil {
    /**
     *
     */
    fun generateLongId(): Long {
        val id = UUID.randomUUID()

        val buffer = ByteBuffer.wrap(ByteArray(16))
        buffer.putLong(id.leastSignificantBits)
        buffer.putLong(id.mostSignificantBits)
        return MurmurHash.hash64(buffer.array())
    }
}