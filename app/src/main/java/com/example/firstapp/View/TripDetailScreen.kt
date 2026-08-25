package com.example.firstapp.View


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firstapp.Model.RouteStop
import com.example.firstapp.Model.StopType
import com.example.firstapp.Model.TripStatus
import com.example.firstapp.ViewModel.TripDetailEvent
import com.example.firstapp.ViewModel.TripDetailViewModel

private val Orange = Color(0xFFE67C2A)
private val BleuBic = Color(0xFF0B1D36)
private val Blanc = Color.White
private val BleuClair = Color(0xFF4A90E2)
private val TripCardColor = Blanc
private val TripFieldColor = BleuClair
private val TripBorderColor = Blanc
private val TripTextColor = BleuBic
private val TripSecondaryText = BleuBic


@Composable
fun TripDetailScreen(
    onBack: () -> Unit,
    viewModel: TripDetailViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val trip = state.trip

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Blanc)
            .padding(vertical = 50.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Surface(
                color = TripCardColor,
                border = BorderStroke(1.dp, TripBorderColor)
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(65.dp)
                            .padding(horizontal = 14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Retour",
                                tint = TripTextColor
                            )
                        }

                        Text(
                            text = "Détail du trajet",
                            color = TripTextColor,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }


                    HorizontalDivider(color = TripBorderColor)

                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(18.dp)
                    ) {
                        Surface(
                            color = TripFieldColor,
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp, vertical = 17.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TripInformation(
                                    modifier = Modifier.weight(1f),
                                    label = "Distance",
                                    value = trip.distance
                                )

                                TripInformation(
                                    modifier = Modifier.weight(1f),
                                    label = "Durée est.",
                                    value = trip.estimatedDuration
                                )

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "Statut",
                                        color = TripSecondaryText,
                                        style = MaterialTheme.typography.labelMedium
                                    )
                                    TripStatusPill(trip.status)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(90.dp))

                        RouteTimeline(trip.stops)

                        HorizontalDivider(color = TripBorderColor)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                modifier = Modifier.size(45.dp),
                                color = Color(0xFF002E5E),
                                shape = CircleShape
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = trip.driver.initials,
                                        color = Color(0xFF8EC7FF),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 12.dp)
                            ) {
                                Text(
                                    text = trip.driver.name,
                                    color = TripTextColor,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = trip.driver.role,
                                    color = TripSecondaryText
                                )
                            }

                            Icon(
                                imageVector = Icons.Default.LocalShipping,
                                contentDescription = null,
                                tint = TripSecondaryText,
                                modifier = Modifier.size(18.dp)
                            )

                            Text(
                                text = trip.driver.vehicleNumber,
                                color = TripTextColor,
                                modifier = Modifier.padding(start = 5.dp),
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Spacer(modifier = Modifier.height(90.dp))


                        if (state.deliveryMarked) {
                            Text(
                                text = "✓ Livraison marquée comme livrée.",
                                color = Color(0xFF9CDBAF),
                                fontWeight = FontWeight.Medium
                            )
                        }

                        Button(
                            onClick = {
                                viewModel.onEvent(TripDetailEvent.MarkAsDelivered)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(53.dp),
                            shape = RoundedCornerShape(12.dp),
                            enabled = trip.status == TripStatus.IN_PROGRESS,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = BleuBic,
                                contentColor = BleuClair,
                                disabledContainerColor = Color(0xFF315A3E),
                                disabledContentColor = Color(0xFFB4E5C0)
                            )
                        ) {
                            Text(
                                text = if (trip.status == TripStatus.IN_PROGRESS) {
                                    "Marquer comme livrée"
                                } else {
                                    "Livraison terminée"
                                },
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

        }
    }
}

@Composable
private fun TripInformation(
    modifier: Modifier = Modifier,
    label: String,
    value: String
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            color = TripSecondaryText,
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = value,
            color = TripTextColor,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
private fun TripStatusPill(status: TripStatus) {
    val isInProgress = status == TripStatus.IN_PROGRESS

    Surface(
        color = if (isInProgress) Color(0xFF301F06) else Color(0xFF183222),
        shape = CircleShape,
        border = BorderStroke(
            1.dp,
            if (isInProgress) Color(0xFF633C00) else Color(0xFF4C745C)
        ),
        modifier = Modifier.padding(top = 5.dp)
    ) {
        Text(
            text = status.label,
            color = if (isInProgress) Color(0xFFD98F00) else Color(0xFF9CDBAF),
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun RouteTimeline(stops: List<RouteStop>) {
    Column {
        stops.forEachIndexed { index, stop ->
            val isLast = index == stops.lastIndex

            Row {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(15.dp)
                            .background(
                                color = when (stop.type) {
                                    StopType.DEPARTURE -> Color(0xFF1976D2)
                                    StopType.INTERMEDIATE -> Color(0xFF20201F)
                                    StopType.ARRIVAL -> Color(0xFF00A52A)
                                },
                                shape = CircleShape
                            )
                            .then(
                                if (stop.type == StopType.INTERMEDIATE) {
                                    Modifier.background(
                                        color = Color(0xFF777673),
                                        shape = CircleShape
                                    )
                                } else Modifier
                            )
                    )

                    if (!isLast) {
                        Box(
                            modifier = Modifier
                                .width(2.dp)
                                .height(46.dp)
                                .background(Color(0xFF5B5B58))
                        )
                    }
                }

                Column(
                    modifier = Modifier.padding(start = 12.dp, bottom = if (isLast) 0.dp else 3.dp)
                ) {
                    Text(
                        text = stop.city,
                        color = TripTextColor,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = stop.detail,
                        color = TripSecondaryText,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}