package com.example.firstapp.View

import com.example.firstapp.ViewModel.NewDeliveryViewModel


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firstapp.Model.DeliveryStatus
import com.example.firstapp.ViewModel.NewDeliveryEvent
import androidx.compose.foundation.background
private val Orange = Color(0xFFE67C2A)
private val BleuBic = Color(0xFF0B1D36)
private val Blanc = Color.White

private val ScreenBackground = Blanc
private val CardBackground = Blanc
private val FieldBackground = Blanc
private val FieldBorder = BleuBic.copy(alpha = 0.18f)
private val MainText = BleuBic
private val SecondaryText = BleuBic.copy(alpha = 0.65f)

private val clients = listOf(
    "Société Diallo & Fils",
    "Kaba Distribution",
    "CAM Logistique"
)

private val vehicles = listOf(
    "Camion GN-2431-A",
    "Fourgon GN-7890-B",
    "Semi-remorque GN-1508-C"
)

@Composable
fun NewDeliveryScreen(
    onBack: () -> Unit,
    viewModel: NewDeliveryViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackground)
            .padding(vertical = 60.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Surface(
                color = CardBackground
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
                                tint = MainText
                            )
                        }

                        Text(
                            text = "Nouvelle livraison",
                            color = MainText,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    HorizontalDivider(color = FieldBorder)

                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        DeliveryDropdown(
                            label = "Client",
                            value = state.client,
                            placeholder = "Sélectionner un client",
                            items = clients,
                            error = state.errors.client,
                            onValueChange = {
                                viewModel.onEvent(NewDeliveryEvent.ClientChanged(it))
                            }
                        )

                        DeliveryDropdown(
                            label = "Véhicule",
                            value = state.vehicle,
                            placeholder = "Sélectionner un véhicule",
                            items = vehicles,
                            error = state.errors.vehicle,
                            onValueChange = {
                                viewModel.onEvent(NewDeliveryEvent.VehicleChanged(it))
                            }
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            DeliveryTextField(
                                modifier = Modifier.weight(1f),
                                label = "Départ",
                                value = state.departure,
                                placeholder = "Conakry",
                                error = state.errors.departure,
                                onValueChange = {
                                    viewModel.onEvent(NewDeliveryEvent.DepartureChanged(it))
                                }
                            )

                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                tint = SecondaryText,
                                modifier = Modifier
                                    .padding(horizontal = 10.dp, vertical = 17.dp)
                                    .size(20.dp)
                            )

                            DeliveryTextField(
                                modifier = Modifier.weight(1f),
                                label = "Arrivée",
                                value = state.destination,
                                placeholder = "Destination",
                                error = state.errors.destination,
                                onValueChange = {
                                    viewModel.onEvent(NewDeliveryEvent.DestinationChanged(it))
                                }
                            )
                        }

                        DeliveryTextField(
                            label = "Marchandise",
                            value = state.merchandise,
                            placeholder = "Description de la marchandise",
                            error = state.errors.merchandise,
                            singleLine = false,
                            onValueChange = {
                                viewModel.onEvent(NewDeliveryEvent.MerchandiseChanged(it))
                            }
                        )

                        Text(
                            text = "Statut",
                            color = MainText,
                            fontWeight = FontWeight.SemiBold
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            StatusButton(
                                label = "En cours",
                                selected = state.status == DeliveryStatus.IN_PROGRESS,
                                onClick = {
                                    viewModel.onEvent(
                                        NewDeliveryEvent.StatusChanged(DeliveryStatus.IN_PROGRESS)
                                    )
                                }
                            )

                            StatusButton(
                                label = "Livrée",
                                selected = state.status == DeliveryStatus.DELIVERED,
                                onClick = {
                                    viewModel.onEvent(
                                        NewDeliveryEvent.StatusChanged(DeliveryStatus.DELIVERED)
                                    )
                                }
                            )
                        }

                        if (state.saved) {
                            Text(
                                text = "✓ Livraison enregistrée.",
                                color = Color(0xFF9CDBAF),
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Spacer(modifier = Modifier.height(60.dp))

                        Button(
                            onClick = { viewModel.onEvent(NewDeliveryEvent.Save) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(53.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Orange,
                                contentColor = Blanc
                            )
                        ) {
                            Text(
                                text = "Enregistrer la livraison",
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
private fun StatusButton(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) Color(0xFF301F06) else FieldBackground,
            contentColor = if (selected) Orange else SecondaryText
        ),
        border = BorderStroke(
            1.dp,
            if (selected) Color(0xFF633C00) else FieldBorder
        ),
        contentPadding = ButtonDefaults.ContentPadding
    ) {
        Text(label, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun DeliveryTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    placeholder: String,
    error: String?,
    singleLine: Boolean = true,
    onValueChange: (String) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            color = MainText,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 7.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = if (singleLine) 52.dp else 62.dp),
            placeholder = { Text(placeholder, color = SecondaryText) },
            singleLine = singleLine,
            isError = error != null,
            shape = RoundedCornerShape(12.dp),
            colors = deliveryFieldColors()
        )

        error?.let {
            Text(it, color = Color(0xFFE99A8C), style = MaterialTheme.typography.labelSmall)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DeliveryDropdown(
    label: String,
    value: String,
    placeholder: String,
    items: List<String>,
    error: String?,
    onValueChange: (String) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Column {
        Text(
            text = label,
            color = MainText,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 7.dp)
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .height(52.dp),
                placeholder = { Text(placeholder, color = SecondaryText) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                isError = error != null,
                shape = RoundedCornerShape(12.dp),
                colors = deliveryFieldColors()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = {
                            onValueChange(item)
                            expanded = false
                        }
                    )
                }
            }
        }

        error?.let {
            Text(it, color = Color(0xFFE99A8C), style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
private fun deliveryFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedContainerColor = FieldBackground,
    unfocusedContainerColor = FieldBackground,
    focusedTextColor = MainText,
    unfocusedTextColor = MainText,
    focusedBorderColor = Color(0xFF77746D),
    unfocusedBorderColor = FieldBorder,
    errorBorderColor = Color(0xFFB85D50),
    cursorColor = MainText
)