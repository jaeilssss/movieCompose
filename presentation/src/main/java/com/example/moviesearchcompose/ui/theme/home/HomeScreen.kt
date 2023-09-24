package com.example.moviesearchcompose.ui.theme.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.moviesearchcompose.R


@Composable
fun HomeScreen(){

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.ic_home_movie)
    )
    val lottieAnimatable = rememberLottieAnimatable()


    LaunchedEffect(composition) {
        lottieAnimatable.animate(
            composition = composition,
            clipSpec = LottieClipSpec.Frame(0, 1200),
            initialProgress = 0f
        )
    }



    CompositionLocalProvider() {
        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(id = R.color.main_color))) {
            val (lottie, title, btn) = createRefs()
            createVerticalChain(lottie,title,btn)

            LottieAnimation(modifier = Modifier
                .size(150.dp)
                .constrainAs(lottie) {
                    start.linkTo(parent.start, 10.dp)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(btn.top)
                },composition = composition,
                progress = lottieAnimatable.progress,
                contentScale = ContentScale.FillBounds)
            Button(modifier = Modifier.constrainAs(btn){
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(lottie.bottom)
                bottom.linkTo(parent.bottom)
            }, onClick = { /*TODO*/ },) {
                Text(text = "Go")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun preview(){
    HomeScreen()
}