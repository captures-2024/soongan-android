package com.captures2024.soongan.core.model.enums

enum class AwardsPostStatusType {
    ACTIVE,
    BLINDED,
    DELETED_BY_ADMIN,
    DELETED_BY_CREATOR,
    ;

    companion object {
        fun from(value: String?): AwardsPostStatusType = when (value) {
            BLINDED.name -> BLINDED
            DELETED_BY_ADMIN.name -> DELETED_BY_ADMIN
            DELETED_BY_CREATOR.name -> DELETED_BY_CREATOR
            else -> ACTIVE
        }
    }
}
