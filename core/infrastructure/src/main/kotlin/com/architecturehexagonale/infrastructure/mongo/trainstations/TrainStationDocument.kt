package com.architecturehexagonale.infrastructure.mongo.trainstations

import com.architecturehexagonale.domain.trainstations.entities.TrainStation
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "train_stations")
data class TrainStationDocument(
    @Id val id: String?,
    val code: String?,
    var label: String?
) {
    fun toDomainInstance() = TrainStation(
        code.orEmpty(),
        label.orEmpty()
    )

    constructor(trainStation: TrainStation) : this(
        id = null,
        trainStation.code,
        trainStation.label
    )
}
