package com.captures2024.soongan.feature.home.utils

enum class ReportType {
    NONE, // 기본 상태
    FINISH, // 완료 상태
    INAPPROPRIATE_IMAGE_OR_LANGUAGE, // 부적절한 사진 게시 및 언행
    HATE_SPEECH, // 욕설, 혐오, 비하 등이 포함된 표현
    COPYRIGHT_INFRINGEMENT, // 도용, 초상권, 저작권 등 타인의 권리 침해
    SPAM, // 게시물 도배
    PROMOTION, // 홍보용 사진 혹은 댓글 게시
    OTHER // 기타
}