package com.andremw96.qocrkmm.ui

import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.ImageBitmap
import com.andremw96.qocrkmm.toImageBitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.nio.ByteBuffer

@ExperimentalGetImage
fun generateTextFromImage(
    imageProxy: ImageProxy,
    rotationDegrees: Int,
    onTextGenerated: (text: String, image: ImageBitmap?) -> Unit,
    capturePhotoStarted: MutableState<Boolean>,
) {
    if (imageProxy.image != null) {
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        val byteArray: ByteArray = imageProxy.planes[0].buffer.toByteArray()
        val imageBitmap = byteArray.toImageBitmap()

        val image = InputImage.fromMediaImage(
            imageProxy.image!!,
            rotationDegrees
        )

        recognizer.process(image)
            .addOnSuccessListener { firebaseVisionText ->
                val stringBuilder = StringBuilder()
                for (block in firebaseVisionText.textBlocks) {
                    stringBuilder.append(block.text)
                    stringBuilder.append("\n\n")
                }
                onTextGenerated(stringBuilder.toString(), imageBitmap)
                capturePhotoStarted.value = false
            }
            .addOnFailureListener { e ->
                onTextGenerated(e.localizedMessage, imageBitmap)
                capturePhotoStarted.value = false
            }
    } else {
        onTextGenerated("text not found", null)
        capturePhotoStarted.value = false
    }
}

private fun ByteBuffer.toByteArray(): ByteArray {
    rewind()    // Rewind the buffer to zero
    val data = ByteArray(remaining())
    get(data)   // Copy the buffer into a byte array
    return data // Return the byte array
}
