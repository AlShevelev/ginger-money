package com.syleiman.gingermoney.dto.enums

enum class Color(val value: Byte) {
    BLACK(0),
    WHITE(1),
    RED(2),
    PINK(3),
    PURPLE(4),
    DEEP_PURPLE(5),
    INDIGO(6),
    BLUE(7),
    LIGHT_BLUE(8),
    CYAN(9),
    TEAL(10),
    GREEN(11),
    LIGHT_GREEN(12),
    LIME(13),
    YELLOW(14),
    AMBER(15),
    ORANGE(16),
    DEEP_ORANGE(17);

    companion object Create {
        fun from(sourceValue: Byte): Color = values().first { it.value == sourceValue }
    }
}