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
implementation 'io.github.sagar-viradiya:koreography:0.1.0'
```

## Usage

Creating koreography is the process of recording moves that can be either parallel or sequential. You can declare complex choreography through clean and concise Kotlin DSL. 

### Choreographying sequential animation

```kotlin
val koreography = rememberKoreography {
    move(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = tween(500)
    ) { value, velocity ->
        // Update the state value used for animation here
    }
    
    move(
        initialValue = 0f,
        targetValue = 2f,
        animationSpec = tween(500)
    ) { value, velocity ->
        // Update the state value used for animation here
    }
}
```

> The `move` call is identical to `animate` suspend function of compose except it is not suspend function since, creating koregraphy is just a process of recording moves

### Choreographying parallel animation

```kotlin
val koreography = rememberKoreography {
    parallelMoves {
        move(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = tween(500)
        ) { value, velocity ->
            // Update the state value used for animation here
        }
        
        move(
            initialValue = 0f,
            targetValue = 2f,
            animationSpec = tween(500)
        ) { value, velocity ->
            // Update the state value used for animation here
        }
    }
}
```

### Complex choreography
You can have a nested hierarchy of moves to create complex choreography. The example below has three animations running parallelly and out of them, the last one has two animations within running sequentially.

```kotlin
val koreography = rememberKoreography {
    parallelMoves {
        move(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = tween(500)
        ) { value, velocity ->
            // Update the state value used for animation here
        }
        
        move(
            initialValue = 0f,
            targetValue = 2f,
            animationSpec = tween(500)
        ) { value, velocity ->
            // Update the state value used for animation here
        }
        
        sequentialMoves {
            move(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = tween(500)
            ) { value, velocity ->
                // Update the state value used for animation here
            }
            
            move(
                initialValue = 0f,
                targetValue = 2f,
                animationSpec = tween(500)
            ) { value, velocity ->
                // Update the state value used for animation here
            }
        }
    }
}
``` 

Once `Koreography` is ready it's time to dance! 💃🕺

```kotlin
// Composable scope
koreography.dance(rememberCoroutineScope())
```

> Please note the `rememberCoroutineScope()` passed as a scope. Make sure you pass coroutine scope which will get cleared once you exit composition.


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

koreography.dance(rememberCoroutineScope())
```

### Example 2

The following animation consists of two animations running sequentially. Each has three animations running parallelly (Scale + Rotate + Fade) 

https://user-images.githubusercontent.com/11586051/190526868-9b539423-9240-4f1c-a230-a79c1d0a94e0.mp4

The code for choreographing above animation is there in the sample app.

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
