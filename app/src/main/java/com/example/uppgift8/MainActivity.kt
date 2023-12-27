package com.example.uppgift8

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class MainActivity : ComponentActivity() {
    var imageText = mutableStateOf("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            View()
        }
    }

    fun runTextRecognition(imageBitMap: Bitmap) {
        val image = InputImage.fromBitmap(imageBitMap, 0)
        var textRecognizerOptions = TextRecognizerOptions.Builder().build()
        val recognizer = TextRecognition.getClient(textRecognizerOptions)
        recognizer.process(image)
            .addOnSuccessListener { texts ->
                processTextRecognitionResult(texts)
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
    }

    fun processTextRecognitionResult(texts: Text) {
        val blocks: List<Text.TextBlock> = texts.getTextBlocks()
        if (blocks.size == 0) {
            return
        }
        for (i in blocks.indices) {
            val lines: List<Text.Line> = blocks[i].getLines()
            for (j in lines.indices) {
                val elements: List<Text.Element> = lines[j].getElements()
                elements.forEach {
                    imageText.value = it.text
                }
            }
        }
    }

    @Composable
    fun View() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
        ) {
            Text()
            PrimaryButton()
            SecondaryButton()
            ThirdButton()
        }
    }

    @Composable
    fun Text() {
        Text(
            imageText.value,
            fontSize = 30.sp,
            modifier = Modifier.padding(50.dp)
            )
    }

    @Composable
    fun PrimaryButton() {
        Button(
            onClick = {
                      val selectedImage = BitmapFactory.decodeResource(resources, R.drawable.audi)
               runTextRecognition(imageBitMap = selectedImage)
            },
            modifier = Modifier.size(width = 120.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(Color.DarkGray),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text("First")
        }
    }

    @Composable
    fun SecondaryButton() {
        Button(
            onClick = {
                val selectedImage = BitmapFactory.decodeResource(resources, R.drawable.android)
                runTextRecognition(imageBitMap = selectedImage)
            },
            modifier = Modifier.size(width = 120.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(Color.DarkGray),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text("Second")
        }
    }

    @Composable
    fun ThirdButton() {
        Button(
            onClick = {
                val selectedImage = BitmapFactory.decodeResource(resources, R.drawable.iphone)
                runTextRecognition(imageBitMap = selectedImage)
            },
            modifier = Modifier.size(width = 120.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(Color.DarkGray),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text("Third")
        }
    }
}

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
      MainActivity().View()
    }