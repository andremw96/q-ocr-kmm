package com.andremw96.qocrkmm

import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.yield
import platform.Foundation.*
import platform.UIKit.UIImage
import platform.UIKit.UIImageJPEGRepresentation
import platform.UIKit.UIImagePNGRepresentation
import platform.posix.memcpy

suspend fun NSURL.readData(): NSData {
    while (true) {
        val data = NSData.dataWithContentsOfURL(this)
        if (data != null)
            return data
        yield()
    }
}

suspend fun NSURL.readBytes(): ByteArray =
    with(readData()) {
        ByteArray(length.toInt()).apply {
            usePinned {
                memcpy(it.addressOf(0), bytes, length)
            }
        }
    }

fun getTempImageURL(image: UIImage): NSURL? {
    val imageURL = NSURL(fileURLWithPath = NSTemporaryDirectory()).URLByAppendingPathComponent("TemptImage.png")

    if (imageURL != null) {
        UIImageJPEGRepresentation(image, 50.0)?.writeToURL(imageURL, true)
    }
    return imageURL
}
