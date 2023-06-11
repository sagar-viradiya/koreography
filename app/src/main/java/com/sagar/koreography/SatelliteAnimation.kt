/*
 * Copyright 2022 Koreography Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sagar.koreography

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.github.sagar_viradiya.rememberKoreography
import org.json.JSONArray

@Composable
fun SatelliteAnimation() {

    val endIndex = remember {
        Animatable(178f)
    }

    val translateY = remember {
        Animatable(0f)
    }

    val koreography = rememberKoreography {
        parallelMoves {
            move(
                animatable = endIndex,
                targetValue = 0f,
                animationSpec = infiniteRepeatable(
                    animation = tween(3000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )
            move(
                animatable = translateY,
                targetValue = -60f,
                animationSpec = infiniteRepeatable(
                    animation = tween(2000, easing = FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = 16.dp,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                val path = remember {
                    Path()
                }
                val points = remember {
                    parse()
                }
                Image(
                    modifier = Modifier.graphicsLayer {
                        translationY = translateY.value
                    },
                    painter = painterResource(id = R.drawable.planet),
                    contentDescription = null
                )

                Canvas(modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        translationY = translateY.value
                    }) {
                    val firstPoint = points.first()
                    path.moveTo(firstPoint.x, firstPoint.y)
                    for (i in 1 until points.size) {
                        path.lineTo(points[i].x, points[i].y)
                    }
                    val stroke = Stroke(
                        width = 5f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 20f), 0f)
                    )

                    drawPath(path = path, style = stroke, color = Color.Black)
                }
                Image(
                    modifier = Modifier.graphicsLayer {
                        val point = points[endIndex.value.toInt()]
                        translationX = point.x - (57.dp.toPx())
                        translationY = point.y - (57.dp.toPx()) + translateY.value
                    },
                    painter = painterResource(id = R.drawable.rocket),
                    contentDescription = null
                )
            }
        }
    }

    LaunchedEffect(true) {
        koreography.dance(this)
    }
}

fun parse(): List<Offset> {
    val jsonArray = JSONArray(
        "[[563.8410186767578,170.3678970336914],[548.9752807617188,172.51342391967773],[534.2152862548828,175.2982292175293],[519.5606231689453,178.59387588500977],[505.00927734375,182.32001495361328],[490.5596923828125,186.4233627319336],[476.21067810058594,190.8658905029297],[461.9620056152344,195.62054443359375],[447.81427001953125,200.66766357421875],[433.76829528808594,205.991455078125],[419.82591247558594,211.5809783935547],[405.98931884765625,217.4274444580078],[392.2609405517578,223.5237579345703],[378.64402770996094,229.86520385742188],[365.14219665527344,236.4480972290039],[351.75977325439453,243.27051544189453],[338.50099182128906,250.3301010131836],[325.37091064453125,257.62628173828125],[312.3750686645508,265.15894317626953],[299.51941680908203,272.92845153808594],[286.81034088134766,280.9355163574219],[274.25484466552734,289.18121337890625],[261.8606414794922,297.66746520996094],[249.63603973388672,306.3961944580078],[237.58898162841797,315.3683624267578],[225.72903442382812,324.58641815185547],[214.06665802001953,334.0532684326172],[202.61105346679688,343.7692337036133],[191.37512969970703,353.73836517333984],[180.36859130859375,363.9602737426758],[169.6054916381836,374.4381408691406],[159.09828186035156,385.172607421875],[148.86058044433594,396.1643829345703],[138.90662384033203,407.4137420654297],[129.20966720581055,418.8855285644531],[119.73125839233398,430.5384521484375],[110.48638916015625,442.3775939941406],[101.49040603637695,454.4068908691406],[92.76020050048828,466.6303253173828],[84.3134937286377,479.05137634277344],[76.16944313049316,491.6729278564453],[68.34881401062012,504.497314453125],[60.87393379211426,517.5261383056641],[53.76850891113281,530.7600860595703],[47.056480407714844,544.1977844238281],[40.76502513885498,557.83740234375],[34.92095947265625,571.6744995117188],[29.55377769470215,585.7034454345703],[24.691972732543945,599.9153137207031],[20.366281986236572,614.2992553710938],[16.605935096740723,628.8412628173828],[13.441509246826172,643.5243530273438],[10.900945901870728,658.3280181884766],[9.011743783950806,673.2288665771484],[7.798051357269287,688.1996612548828],[7.282416343688965,703.2107391357422],[7.482039928436279,718.2292327880859],[8.411012649536133,733.2202606201172],[10.07970929145813,748.1471557617188],[12.492995738983154,762.9716949462891],[15.651251792907715,777.6556091308594],[19.55216360092163,792.1595764160156],[24.189448356628418,806.4453735351562],[29.555105209350586,820.4738159179688],[35.64249515533447,834.2046203613281],[42.44395351409912,847.5958557128906],[49.96350288391113,860.59716796875],[58.21751403808594,873.1447448730469],[67.26536750793457,885.1313781738281],[77.32843780517578,896.2720642089844],[88.45214653015137,906.362548828125],[100.19288635253906,915.7286682128906],[112.45366287231445,924.4036560058594],[125.15675354003906,932.4175415039062],[138.23780822753906,939.7987976074219],[151.64325714111328,946.5734252929688],[165.32908630371094,952.76220703125],[179.25749588012695,958.3845520019531],[193.39540100097656,963.45703125],[207.71477508544922,967.9925537109375],[222.1900405883789,972.0025634765625],[236.79835510253906,975.4977722167969],[251.51900482177734,978.4838562011719],[266.33264923095703,980.9682312011719],[281.2211608886719,982.9554748535156],[296.16737365722656,984.4494323730469],[311.1544189453125,985.4526672363281],[326.16625213623047,985.9674682617188],[341.18687438964844,985.9953918457031],[356.2005615234375,985.5380859375],[371.1914749145508,984.5952758789062],[386.1441650390625,983.1681518554688],[401.04295349121094,981.2589111328125],[415.8717498779297,978.8658142089844],[430.6150817871094,975.9927978515625],[445.25669860839844,972.64013671875],[459.78076171875,968.8097534179688],[474.17156982421875,964.5059509277344],[488.4132843017578,959.73193359375],[502.49024963378906,954.4918212890625],[516.3901519775391,948.7985229492188],[530.1328582763672,942.7350769042969],[543.7341156005859,936.3604431152344],[557.1985931396484,929.7018127441406],[570.5299530029297,922.7799682617188],[583.7287445068359,915.6091918945312],[596.7951965332031,908.1990966796875],[609.7271575927734,900.5575561523438],[622.5218353271484,892.688232421875],[635.1766662597656,884.5953369140625],[647.6863403320312,876.2804260253906],[660.0464172363281,867.7442321777344],[672.2511291503906,858.9876708984375],[684.2941589355469,850.0099182128906],[696.167724609375,840.8094177246094],[707.8648681640625,831.3857116699219],[719.3775329589844,821.737060546875],[730.6946868896484,811.8601684570312],[741.8082275390625,801.7546691894531],[752.7057495117188,791.4166259765625],[763.3772735595703,780.8453979492188],[773.8082885742188,770.0370483398438],[783.9873962402344,758.9913024902344],[793.8998107910156,747.7050933837891],[803.5296936035156,736.1772766113281],[812.8628540039062,724.40771484375],[821.8815307617188,712.3958129882812],[830.5676879882812,700.14111328125],[838.9045715332031,687.6464080810547],[846.8735046386719,674.9140777587891],[854.4548034667969,661.9468688964844],[861.6289672851562,648.7501373291016],[868.3770446777344,635.3304748535156],[874.6788940429688,621.6959838867188],[880.5167541503906,607.8561401367188],[885.872314453125,593.8229370117188],[890.72900390625,579.6092376708984],[895.1101684570312,565.2417297363281],[899.1252136230469,550.7674713134766],[902.7584838867188,536.1925506591797],[905.9794006347656,521.5213165283203],[908.7561950683594,506.7593994140625],[911.0555419921875,491.915771484375],[912.8394470214844,477.0015563964844],[914.0696411132812,462.03172302246094],[914.7046508789062,447.02508544921875],[914.6994323730469,432.0048065185547],[914.011962890625,417.00086975097656],[912.598388671875,402.0476989746094],[910.4154968261719,387.18772888183594],[907.4267578125,372.4686584472656],[903.5997619628906,357.94537353515625],[898.9096984863281,343.6776351928711],[893.3438415527344,329.72806549072266],[886.9009094238281,316.16175842285156],[879.5930786132812,303.04087829589844],[871.4445190429688,290.4250946044922],[862.4938659667969,278.36487579345703],[852.7882690429688,266.9036636352539],[842.3823852539062,256.07391357421875],[831.3362731933594,245.89752960205078],[819.7116394042969,236.3873291015625],[807.56982421875,227.54647064208984],[794.9697875976562,219.3716812133789],[781.9667358398438,211.8541488647461],[768.611572265625,204.9814453125],[754.9506683349609,198.73853302001953],[741.0246734619141,193.11044311523438],[726.87158203125,188.08071899414062],[712.5242614746094,183.63458633422852],[698.0121917724609,179.7605438232422],[683.3616943359375,176.44675827026367],[668.5970306396484,173.68715286254883],[653.7400360107422,171.47799682617188],[638.8111724853516,169.8204116821289],[623.8311309814453,168.72235107421875],[608.8196868896484,168.19725036621094],[593.7996368408203,168.26937103271484],[578.7960205078125,168.97497940063477]]"
    )
    val points = mutableListOf<Offset>()
    for (i in 0 until jsonArray.length()) {
        val coordinate = jsonArray.optJSONArray(i)
        val x = coordinate.optDouble(0).toFloat()
        val y = coordinate.optDouble(1).toFloat()
        points += Offset(x, y)
    }
    return points
}
