package com.captures2024.soongan.core.model.enums

enum class ContestStatus {
    UPCOMING,
    IN_PROGRESS,
    CLOSED,
    ;

    companion object {

        fun from(status: String): ContestStatus = when (status.uppercase()) {
            "UPCOMING" -> UPCOMING
            "IN_PROGRESS" -> IN_PROGRESS
            else -> CLOSED
        }
    }
}
