package com.mercadolibre.android.cardform.domain

import com.mercadolibre.android.cardform.base.UseCase

internal class AssociateCardUseCase(
    private val cardAssociationRepository: CardAssociationRepository
) : UseCase<AssociateCardParam, AssociatedCardBM>() {

    override suspend fun doExecute(param: AssociateCardParam) = cardAssociationRepository.associateCard(param)
}

internal data class AssociateCardParam(
    val cardTokenId: String,
    val paymentMethodId: String,
    val paymentMethodType: String,
    val issuerId: Int
)
