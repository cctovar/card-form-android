package com.mercadolibre.android.cardform.domain

import android.os.Parcelable
import com.mercadolibre.android.cardform.base.ResponseCallback
import kotlinx.android.parcel.Parcelize

internal interface CardAssociationRepository {
    suspend fun associateCard(param: AssociateCardParam): ResponseCallback<AssociatedCardBM>
}

@Parcelize
internal data class AssociatedCardBM(
    val id: String,
    val enrollmentSuggested: Boolean
) : Parcelable
