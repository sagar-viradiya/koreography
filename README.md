# Koreography
A lightweight Compose Animation utility library to choreograph low-level Animation API through Kotlin DSL. It does the heavy lifting of dealing with coroutines under the hood so that you can focus on your animation choreography.

## Including in your project
Add the following dependency in your `build.gradle` file.

```groovy
implementation 'io.github.sagar-viradiya:koreography:0.1.0'
```

## Usage

You can write complex choreography through clean and concise Kotlin DSL. 

### Example 1
The following example consists of two animations running sequentially having two parallel animations (Scale + Fade) within each. The first animation fades in alpha value and scales up the image. The second fade out and scale the image.

https://user-images.githubusercontent.com/11586051/190523592-692c400f-cec6-48ea-9e0e-f641415bc25f.mp4

```kotlin
// Composable scope

var alpha by remember { mutableStateOf(0f) }
var scale by remember { mutableStateOf(0f) }

val koreography = rememberKoreography {
    parallelMoves {
        move(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = tween(500)
        ) { value, _ ->
            alpha = value
        }
        move(
            initialValue = 0f,
            targetValue = 2f,
            animationSpec = tween(500)
        ) { value, _ ->
            scale = value
        }
    }
    parallelMoves {
        move(
            initialValue = 1f,
            targetValue = 0f,
            animationSpec = tween(500)
        ) { value, _ ->
            alpha = value
        }
        move(
            initialValue = 2f,
            targetValue = 4f,
            animationSpec = tween(500)
        ) { value, _ ->
            scale = value
        }
    }
}
```

Once `Koreography` is ready it's time to dance! 💃🕺

```kotlin
// Composable scope
koreography.dance(rememberCoroutineScope())
```

Please note the `rememberCoroutineScope()` passed as a scope. Make sure you pass coroutine scope which will get cleared once you exit composition.

### Example 2

The following animation consists of two animations running sequentially. Each has three animations running parallelly (Scale + Rotate + Fade) 

https://user-images.githubusercontent.com/11586051/190526868-9b539423-9240-4f1c-a230-a79c1d0a94e0.mp4

The code for choreographing above animation is there in the sample app.

## Contribution

This is the early preview and unfortunately it is not ready to accept any contribution yet. Once this is stable enough contribution guidelines will be updated here.

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
