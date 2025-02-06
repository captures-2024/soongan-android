package com.captures2024.soongan.core.model.dto

data class ReportInfoDto(
    val id: Long,
    val reportMemberId: Long,
    val targetMemberId: Long,
    val targetId: Long,
    val targetType: String,
    val reportType: String,
    val reason: String? = null,
)