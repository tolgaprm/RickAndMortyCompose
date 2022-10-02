package com.prmto.rickandmortycompose.util

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview


@Preview(device = Devices.PIXEL_3A, name = "Phone", showSystemUi = true)
@Preview(device = Devices.NEXUS_10, name = "Tablet", fontScale = 1.5f, showSystemUi = true)
annotation class MultiDevicePreview


@Preview(device = Devices.PIXEL_3A, name = "Phone", showBackground = true)
@Preview(device = Devices.NEXUS_9, name = "Tablet", fontScale = 2f, showBackground = true)
annotation class MultiDevicePreviewWithOutSystemUI