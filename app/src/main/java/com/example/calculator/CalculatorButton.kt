package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(symbol:String,
                     modifier: Modifier,
                     color: Color= Color.White,
                     textStyle: TextStyle=TextStyle(),
                     onClick:()->Unit){
Box(modifier = modifier
    .clip(CircleShape)
    .background(color).clickable {
        onClick()
    }
    .then(modifier),
    contentAlignment = Alignment.Center,

){
Text(text = symbol,
    style = textStyle,
    color = Color.White,
    fontSize = 36.sp)
}

}