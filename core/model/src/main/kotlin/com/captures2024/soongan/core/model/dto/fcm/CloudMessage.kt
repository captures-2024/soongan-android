package com.captures2024.soongan.core.model.dto.fcm

import com.captures2024.soongan.core.model.utils.NotificationType

sealed interface CloudMessage {

    data class CommentMessageDto(
        val title: String,
        val body: String,
        val notificationType: NotificationType,
        val postId: Long,
        val timestamp: String,
    ) : CloudMessage {

        companion object {
            fun fromPayload(payload: Map<String, Any?>): CommentMessageDto? = runCatching {
                CommentMessageDto(
                    title = payload["title"]!!.toString(),
                    body = payload["body"]!!.toString(),
                    notificationType = NotificationType.fromString(payload[CommentMessageDto::notificationType.name]!!.toString())!!,
                    postId = payload[CommentMessageDto::postId.name]!!.toString().toLong(),
                    timestamp = payload[CommentMessageDto::timestamp.name]!!.toString(),
                )
            }.getOrNull()
        }
    }

    data class NeedExplainMessageDto(
        val title: String,
        val body: String,
        val notificationType: NotificationType,
        val targetId: Long,
        val targetType: String,
        val timestamp: String,
    ) : CloudMessage {

        companion object {
            fun fromPayload(payload: Map<String, Any?>): NeedExplainMessageDto? = runCatching {
                NeedExplainMessageDto(
                    title = payload["title"]!!.toString().takeIf { it.contains("소명") }!!,
                    body = payload["body"]!!.toString(),
                    notificationType = NotificationType.fromString(payload[NeedExplainMessageDto::notificationType.name]!!.toString())!!,
                    targetId = payload[NeedExplainMessageDto::targetId.name]!!.toString().toLong(),
                    targetType = payload[NeedExplainMessageDto::targetType.name]!!.toString(),
                    timestamp = payload[NeedExplainMessageDto::timestamp.name]!!.toString(),
                )
            }.getOrNull()
        }
    }

    data class BlockMessageDto(
        val title: String,
        val body: String,
        val notificationType: NotificationType,
        val targetId: Long,
        val targetType: String,
        val timestamp: String,
    ) : CloudMessage {

        companion object {
            fun fromPayload(payload: Map<String, Any?>): BlockMessageDto? = runCatching {
                BlockMessageDto(
                    title = payload["title"]!!.toString(),
                    body = payload["body"]!!.toString(),
                    notificationType = NotificationType.fromString(payload[BlockMessageDto::notificationType.name]!!.toString())!!,
                    targetId = payload[BlockMessageDto::targetId.name]!!.toString().toLong(),
                    targetType = payload[BlockMessageDto::targetType.name]!!.toString(),
                    timestamp = payload[BlockMessageDto::timestamp.name]!!.toString(),
                )
            }.getOrNull()
        }
    }

    data class ReportResultMessageDto(
        val title: String,
        val body: String,
        val link: String,
        val notificationType: NotificationType,
        val postId: Long,
        val timestamp: String,
    ) : CloudMessage {

        companion object {
            fun fromPayload(payload: Map<String, Any?>): ReportResultMessageDto? = runCatching {
                ReportResultMessageDto(
                    title = payload["title"]!!.toString(),
                    body = payload["body"]!!.toString(),
                    link = payload[ReportResultMessageDto::link.name]!!.toString(),
                    notificationType = NotificationType.fromString(payload[ReportResultMessageDto::notificationType.name]!!.toString())!!,
                    postId = payload[ReportResultMessageDto::postId.name]!!.toString().toLong(),
                    timestamp = payload[ReportResultMessageDto::timestamp.name]!!.toString(),
                )
            }.getOrNull()
        }
    }

    companion object {
        fun fromPayload(payload: Map<String, Any?>): CloudMessage? = runCatching {
            CommentMessageDto.fromPayload(payload)?.let {
                return@runCatching it
            }

            NeedExplainMessageDto.fromPayload(payload)?.let {
                return@runCatching it
            }

            BlockMessageDto.fromPayload(payload)?.let {
                return@runCatching it
            }

            ReportResultMessageDto.fromPayload(payload)?.let {
                return@runCatching it
            }

            return@runCatching null
        }.getOrNull()
    }
}
