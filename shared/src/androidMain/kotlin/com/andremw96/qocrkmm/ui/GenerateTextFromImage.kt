package com.andremw96.qocrkmm.ui

import android.media.Image
import android.util.Log
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

fun generateTextFromImage(
    imageResult: Image,
    rotationDegrees: Int,
    onTextGenerated: (text: String) -> Unit,
) {
    val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    val image = InputImage.fromMediaImage(
        imageResult,
        rotationDegrees
    )

    recognizer.process(image)
        .addOnSuccessListener { firebaseVisionText ->
            val stringBuilder = StringBuilder()
            for (block in firebaseVisionText.textBlocks) {
                stringBuilder.append(block.text)
                stringBuilder.append("\n\n")
            }
            onTextGenerated(stringBuilder.toString())
        }
        .addOnFailureListener { e ->
            Log.d("Generated text", e.localizedMessage)
        }
}
