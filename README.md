# Koreography
_Choreograph your Compose Animation 💃🕺_
<br><br>
[![Maven Central](https://img.shields.io/maven-central/v/io.github.sagar-viradiya/koreography)](https://search.maven.org/artifact/io.github.sagar-viradiya/koreography)

[![Github Followers](https://img.shields.io/github/followers/sagar-viradiya?label=Follow&style=social)](https://github.com/sagar-viradiya)
[![Twitter Follow](https://img.shields.io/twitter/follow/viradiya_sagar?label=Follow&style=social)](https://twitter.com/viradiya_sagar)

<br>
A lightweight Compose Animation utility library to choreograph low-level Animation API (https://developer.android.com/jetpack/compose/animation#animation) through Kotlin DSL. It does the heavy lifting of dealing with coroutines under the hood so that you can focus on your animation choreography.

## Including in your project
Koreography is available on `mavenCentral()`

```groovy
implementation 'io.github.sagar-viradiya:koreography:0.3.0'
```

## Usage

Creating choreography is the process of recording moves on [Animatable](https://developer.android.com/jetpack/compose/animation/value-based#animatable), A low level compose animation API. Moves could be either parallel or sequential. A choreography is a composition of such moves that you can declare through clean and concise Kotlin DSL as shown below. 

### Choreographying sequential animation

```kotlin
val alphaAnimatable = remember {
    Animatable(0f)
}

val scaleAnimatable = remember {
    Animatable(0f)
}

val koreography = rememberKoreography {
    move(
        animatable = alphaAnimatable,
        targetValue = 1f,
        animationSpec = tween(500)
    )
    
    move(
        animatable = scaleAnimatable,
        targetValue = 2f,
        animationSpec = tween(500)
    )
}
```

### Choreographying parallel animation

```kotlin
val alphaAnimatable = remember {
    Animatable(0f)
}

val scaleAnimatable = remember {
    Animatable(0f)
}

val koreography = rememberKoreography {
    parallelMoves {
        move(
            animatable = alphaAnimatable,
            targetValue = 1f,
            animationSpec = tween(500)
        )
        
        move(
            animatable = scaleAnimatable,
            targetValue = 2f,
            animationSpec = tween(500)
        )
    }
}
```

### Complex choreography
You can have a nested hierarchy of moves to create complex choreography. The example below has three animations running parallelly and out of them, the last one has two animations within running sequentially.

```kotlin
val alphaAnimatable = remember {
    Animatable(0f)
}

val scaleAnimatable = remember {
    Animatable(0f)
}

val rotationAnimatable = remember {
    Animatable(0f)
}

val translationXAnimatable = remember {
    Animatble(0f)
}

val koreography = rememberKoreography {
    parallelMoves {
        move(
            animatable = alphaAnimatable,
            targetValue = 1f,
            animationSpec = tween(500)
        )
        
        move(
            animatable = scaleAnimatable,
            targetValue = 2f,
            animationSpec = tween(500)
        )
        
        sequentialMoves {
            move(
                animatable = rotationAnimatable,
                targetValue = 20f,
                animationSpec = tween(500)
            )
            
            move(
                animatable = translationXAnimatable,
                targetValue = 200f,
                animationSpec = tween(500)
            )
        }
    }
}
``` 

### Executing choreography

Once choreography is ready it's time to dance! 💃🕺.

#### One time dance

You can execute the choreography once by calling `dance` function. If you wish to get callback after execution of choreography then you can pass trailing lambda.

```kotlin
koreography.dance(scope = coroutineScope) {
    // onDanceFinished : Optional trailing lambda
}
```

#### Repeat dance

You can execute the choreography multiple times by calling `repeatDance` function. Following call executes choreography three times.

```kotlin
koreography.repeatDance(count = 3, scope = coroutineScope) {
    // onDanceFinished : Optional trailing lambda
}
```

#### Inifinite dance

You can execute the choreography forever until composition is alive.

```kotlin
koreography.danceForever(scope = coroutineScope){
    // onDanceFinished : Optional trailing lambda
}
```

> Please note the `coroutineScope` should be obtained through `rememberCoroutineScope()`. Make sure you pass coroutine scope which will get cleared once you exit composition.

### Executing choreography based on state change 🚀

Executing choreography based on state change is also supported. This API is similar to [`LaunchedEffect`](https://developer.android.com/jetpack/compose/side-effects#launchedeffect) API of compose side effects.

```kotlin
val alphaAnimatable = remember {
    Animatable(0f)
}

val scaleAnimatable = remember {
    Animatable(0f)
}

LaunchKoreography(state) {
    move(
        animatbale = alphaAnimatable,
        targetValue = 1f,
        animationSpec = tween(500)
    )
    
    move(
        animatbale = scaleAnimatable,
        targetValue = 2f,
        animationSpec = tween(500)
    )
}
```

The choreography passed in the trailing lambda above would be executed on every `state` change.

### Samples

There is endless possibilities with power of coroutines and compose animation API! Here are some free lottie animations recreated using koreography. You can find the source code in the sample app.

https://github.com/sagar-viradiya/koreography/assets/11586051/54c80649-f1b7-454e-a8d2-5ca56ffb4ccc

https://github.com/sagar-viradiya/koreography/assets/11586051/75b5c527-34c4-4552-a6a3-8480766fb88e

https://github.com/sagar-viradiya/koreography/assets/11586051/f57ca6f9-e1eb-4e9d-883a-bd8203e81441

https://github.com/sagar-viradiya/koreography/assets/11586051/766f23f1-f21b-4353-91b8-437672e756aa

https://github.com/sagar-viradiya/koreography/assets/11586051/8cdcf0ab-988f-4dde-92e9-cd5a20490bf6

## Contribution

This is the early preview and unfortunately it is not ready to accept any contribution yet. Once this is stable enough contribution guidelines will be updated here. Meanwhile feel free to start [GitHub Discussions](https://github.com/sagar-viradiya/koreography/discussions) for feature request and improvements.

## License

    Copyright 2022 Koreography Contributors

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
