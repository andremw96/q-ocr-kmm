package com.andremw96.qocrkmm.ui

import android.graphics.Bitmap
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.nio.ByteBuffer

@ExperimentalGetImage
fun generateTextFromImage(
    imageBitmap: Bitmap,
    rotationDegrees: Int,
    onTextGenerated: (text: String, image: ImageBitmap?) -> Unit,
    generateTextStarted: MutableState<Boolean>,
) {
    val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    val image = InputImage.fromBitmap(
        imageBitmap,
        rotationDegrees
    )

    recognizer.process(image)
        .addOnSuccessListener { firebaseVisionText ->
            val stringBuilder = StringBuilder()
            for (block in firebaseVisionText.textBlocks) {
                stringBuilder.append(block.text)
                stringBuilder.append("\n\n")
            }
            onTextGenerated(stringBuilder.toString(), imageBitmap.asImageBitmap())
            generateTextStarted.value = false
        }
        .addOnFailureListener { e ->
            onTextGenerated(e.localizedMessage, imageBitmap.asImageBitmap())
            generateTextStarted.value = false
        }
}

private fun ByteBuffer.toByteArray(): ByteArray {
    rewind()    // Rewind the buffer to zero
    val data = ByteArray(remaining())
    get(data)   // Copy the buffer into a byte array
    return data // Return the byte array
}
