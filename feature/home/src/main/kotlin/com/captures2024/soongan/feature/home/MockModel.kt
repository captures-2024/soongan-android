package com.captures2024.soongan.feature.home

import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.model.mock.GalleryImage
import com.captures2024.soongan.core.model.mock.GridItem

val sampleSkeletonPhotos by lazy {
    List(100) {
        GridItem(
            id = it.toString(),
            size = (100 .. 300).random().dp
        )
    }
}

val samplePhotos by lazy {
    listOf(
        GalleryImage(
            id = 0,
            url = "https://plus.unsplash.com/premium_photo-1673827042837-c9e2076318e9?q=80&w=3387&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 1,
            url = "https://images.unsplash.com/photo-1527237545644-c3d2a74ede9f?q=80&w=3505&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 2,
            url = "https://images.unsplash.com/photo-1529797228130-fe918ce6d915?q=80&w=3387&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 3,
            url = "https://images.unsplash.com/photo-1603072921615-f0d52e2f0c04?q=80&w=3172&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 4,
            url = "https://plus.unsplash.com/premium_photo-1669688173781-7ac8317079dd?q=80&w=3465&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 5,
            url = "https://images.unsplash.com/photo-1621496503717-095a410e1566?q=80&w=3387&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 6,
            url = "https://images.unsplash.com/photo-1590750093844-bc3ae9e48f9c?q=80&w=2404&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 7,
            url = "https://images.unsplash.com/photo-1484517062256-430351efcbcf?q=80&w=3312&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 8,
            url = "https://plus.unsplash.com/premium_photo-1676648534602-db1517105311?q=80&w=3540&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 9,
            url = "https://images.unsplash.com/photo-1612428678564-2573eee0edeb?q=80&w=3387&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 10,
            url = "https://images.unsplash.com/flagged/photo-1553460646-ea0908947276?q=80&w=3137&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 11,
            url = "https://images.unsplash.com/photo-1464746133101-a2c3f88e0dd9?q=80&w=3486&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 12,
            url = "https://plus.unsplash.com/premium_photo-1682432340856-d6cd7cd7090e?q=80&w=3448&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 13,
            url = "https://images.unsplash.com/photo-1542545319-2807785c15b7?q=80&w=3540&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 14,
            url = "https://images.unsplash.com/flagged/photo-1595523668648-6e46bf1dfb95?q=80&w=2000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 15,
            url = "https://images.unsplash.com/photo-1606646677098-46b8141f4da5?q=80&w=3464&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 16,
            url = "https://plus.unsplash.com/premium_photo-1664353833764-09710438adf9?q=80&w=3540&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 17,
            url = "https://images.unsplash.com/photo-1523440915059-aa538cbb821a?q=80&w=3456&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 18,
            url = "https://images.unsplash.com/photo-1543357480-c60d40007a3f?q=80&w=3540&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 19,
            url = "https://images.unsplash.com/photo-1525451350286-a21d5aef139c?q=80&w=1672&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 20,
            url = "https://plus.unsplash.com/premium_photo-1678656484471-dcfff7ed95f6?q=80&w=3387&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 21,
            url = "https://images.unsplash.com/photo-1621367272225-abf78bf7c60a?q=80&w=3434&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 22,
            url = "https://images.unsplash.com/photo-1554311884-415bfda22b47?q=80&w=2602&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 23,
            url = "https://images.unsplash.com/photo-1529586691389-2d3d4132856c?q=80&w=3456&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
        GalleryImage(
            id = 24,
            url = "https://plus.unsplash.com/premium_photo-1704908905651-0d14c96a67f0?q=80&w=3540&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = ""
        ),
    )
}