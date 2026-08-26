package com.example.firstapp.ViewModel

import com.example.firstapp.Model.Delivery
import com.example.firstapp.Model.DeliveryStatus



import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID



data class FormErrors(
    val client: String? = null,
    val vehicle: String? = null,
    val departure: String? = null,
    val destination: String? = null,
    val merchandise: String? = null
) {
    fun hasErrors() = listOf(client, vehicle, departure, destination, merchandise)
        .any { it != null }
}

data class NewDeliveryUiState(
    val client: String = "",
    val vehicle: String = "",
    val departure: String = "Conakry",
    val destination: String = "",
    val merchandise: String = "",
    val status: DeliveryStatus = DeliveryStatus.IN_PROGRESS,
    val errors: FormErrors = FormErrors(),
    val saved: Boolean = false
)

sealed interface NewDeliveryEvent {
    data class ClientChanged(val value: String) : NewDeliveryEvent
    data class VehicleChanged(val value: String) : NewDeliveryEvent
    data class DepartureChanged(val value: String) : NewDeliveryEvent
    data class DestinationChanged(val value: String) : NewDeliveryEvent
    data class MerchandiseChanged(val value: String) : NewDeliveryEvent
    data class StatusChanged(val value: DeliveryStatus) : NewDeliveryEvent
    data object Save : NewDeliveryEvent
}

class NewDeliveryViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(NewDeliveryUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: NewDeliveryEvent) {
        when (event) {
            is NewDeliveryEvent.ClientChanged ->
                update { copy(client = event.value, saved = false) }

            is NewDeliveryEvent.VehicleChanged ->
                update { copy(vehicle = event.value, saved = false) }

            is NewDeliveryEvent.DepartureChanged ->
                update { copy(departure = event.value, saved = false) }

            is NewDeliveryEvent.DestinationChanged ->
                update { copy(destination = event.value, saved = false) }

            is NewDeliveryEvent.MerchandiseChanged ->
                update { copy(merchandise = event.value, saved = false) }

            is NewDeliveryEvent.StatusChanged ->
                update { copy(status = event.value, saved = false) }

            NewDeliveryEvent.Save -> saveDelivery()
        }
    }

    private fun update(transform: NewDeliveryUiState.() -> NewDeliveryUiState) {
        _uiState.update { current ->
            transform(current).copy(errors = FormErrors())
        }
    }

    private fun saveDelivery() {
        val state = _uiState.value

        val errors = FormErrors(
            client = if (state.client.isBlank()) "Sélectionnez un client." else null,
            vehicle = if (state.vehicle.isBlank()) "Sélectionnez un véhicule." else null,
            departure = if (state.departure.isBlank()) "Indiquez le départ." else null,
            destination = if (state.destination.isBlank()) "Indiquez l'arrivée." else null,
            merchandise = if (state.merchandise.isBlank()) "Décrivez la marchandise." else null
        )

        if (errors.hasErrors()) {
            _uiState.update { it.copy(errors = errors) }
            return
        }

        val delivery = Delivery(
            id = UUID.randomUUID().toString(),
            client = state.client,
            vehicle = state.vehicle,
            departure = state.departure,
            destination = state.destination,
            merchandise = state.merchandise,
            status = state.status
        )

        // TODO : enregistrer `delivery` via votre Repository / Room / API Firebase.
        _uiState.update { it.copy(saved = true, errors = FormErrors()) }
    }
}