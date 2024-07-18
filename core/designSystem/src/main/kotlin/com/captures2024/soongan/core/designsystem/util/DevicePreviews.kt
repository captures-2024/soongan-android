package com.captures2024.soongan.core.designsystem.util

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Pixel 5", device = "spec:shape=Normal,width=1080,height=2400,unit=px,dpi=480")
@Preview(name = "Samsung Galaxy S21 Ultra", device = "spec:shape=Normal,width=1440,height=3200,unit=px,dpi=515")
@Preview(name = "small-phone", device = Devices.PIXEL_4A)
@Preview(name = "phone", device = Devices.PHONE)
//@Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
@Preview(name = "foldable", device = "spec:shape=Normal,width=673,height=841,unit=dp,dpi=480")
//@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
annotation class DevicePreviews
