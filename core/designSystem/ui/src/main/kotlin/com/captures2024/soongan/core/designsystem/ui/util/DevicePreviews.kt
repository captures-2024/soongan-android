package com.captures2024.soongan.core.designsystem.ui.util

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

// @Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
// @Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
// @Preview(
//     name = "Pixel 5",
//     device = "spec:width=1080dp,height=2400dp,dpi=480",
//     showBackground = true,
// )
// @Preview(
//     name = "Samsung Galaxy S21 Ultra",
//     device = "spec:width=1440dp,height=3200dp,dpi=515",
//     showBackground = true,
// )
@Preview(
    name = "small-phone",
    device = Devices.PIXEL_4A,
    showBackground = true,
)
@Preview(
    name = "phone",
    device = "spec:width=411dp,height=891dp",
    showBackground = true,
)
// @Preview(
//     name = "foldable",
//     device = "spec:width=673dp,height=841dp,dpi=480",
//     showBackground = true,
// )
annotation class DevicePreviews
