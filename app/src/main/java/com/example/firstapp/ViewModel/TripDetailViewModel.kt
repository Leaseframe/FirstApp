package com.example.firstapp.ViewModel


import androidx.lifecycle.ViewModel
import com.example.firstapp.Model.Driver
import com.example.firstapp.Model.RouteStop
import com.example.firstapp.Model.StopType
import com.example.firstapp.Model.TripDetail
import com.example.firstapp.Model.TripStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class TripDetailUiState(
    val trip: TripDetail = TripDetail(
        distance = "135 km",
        estimatedDuration = "2h 45",
        status = TripStatus.IN_PROGRESS,
        stops = listOf(
            RouteStop("Conakry", "Départ - 06:00", StopType.DEPARTURE),
            RouteStop("Coyah", "Étape intermédiaire", StopType.INTERMEDIATE),
            RouteStop("Forécariah", "Étape intermédiaire", StopType.INTERMEDIATE),
            RouteStop("Kindia", "Arrivée prévue - 08:45", StopType.ARRIVAL)
        ),
        driver = Driver(
            initials = "IS",
            name = "Ibrahima Sow",
            role = "Chauffeur",
            vehicleNumber = "RC-2214-A"
        )
    ),
    val deliveryMarked: Boolean = false
)

sealed interface TripDetailEvent {
    object MarkAsDelivered : TripDetailEvent
}

class TripDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TripDetailUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: TripDetailEvent) {
        when (event) {
            TripDetailEvent.MarkAsDelivered -> markAsDelivered()
        }
    }

    private fun markAsDelivered() {
        // TODO: appeler ici votre Repository / API pour sauvegarder le statut.
        _uiState.update { state ->
            state.copy(
                trip = state.trip.copy(status = TripStatus.DELIVERED),
                deliveryMarked = true
            )
        }
    }
}