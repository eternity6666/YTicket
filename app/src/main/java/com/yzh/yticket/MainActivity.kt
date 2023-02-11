package com.yzh.yticket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yzh.yticket.ui.theme.YTicketTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YTicketTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TicketCard(data = TicketData.mock)
                }
            }
        }
    }
}

data class TicketData(
    val orderId: String,
    val departureStation: String,
    val departureTime: Long,
    val arrivalStation: String,
    val arrivalTime: Long,
    val trainNumber: String,
    val seatInformation: String,
    val price: Double,
    val isKid: Boolean = true,
) {
    companion object {
        val mock = TicketData(
            orderId = "EFZ0206810",
            departureStation = "河源北",
            departureTime = 1400 + 18,
            arrivalStation = "深圳",
            arrivalTime = 1500 + 25,
            trainNumber = "G2781",
            seatInformation = "二等座 不对号入座",
            price = 130.00
        )
    }
}

@Composable
fun TicketCard(data: TicketData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = data.orderId,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StationAndTime(
                    stationName = data.departureStation,
                    time = data.departureTime,
                    alignment = Alignment.Start,
                )
                Text(
                    text = data.trainNumber,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge,
                )
                StationAndTime(
                    stationName = data.arrivalStation,
                    time = data.arrivalTime,
                    alignment = Alignment.End,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = data.seatInformation,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "￥${data.price}",
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

fun Long.formatTime(): String {
    val hour = this / 100
    val minute = this % 100
    val hourString = if (hour < 10) "0$hour" else "$hour"
    val minuteString = if (minute < 10) "0$minute" else "$minute"
    return "$hourString:$minuteString"
}

@Preview
@Composable
fun TicketCard_Previews() {
    YTicketTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
        ) {
            TicketCard(data = TicketData.mock)
        }
    }
}

@Composable
fun StationAndTime(
    stationName: String,
    time: Long,
    alignment: Alignment.Horizontal = Alignment.Start,
) {
    Column(horizontalAlignment = alignment) {
        Text(
            text = stationName,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = time.formatTime(),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}
