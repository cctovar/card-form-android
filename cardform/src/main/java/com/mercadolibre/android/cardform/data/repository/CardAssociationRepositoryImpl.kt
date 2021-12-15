package com.mercadolibre.android.cardform.data.repository

import com.mercadolibre.android.cardform.base.CoroutineContextProvider
import com.mercadolibre.android.cardform.base.Response.Failure
import com.mercadolibre.android.cardform.base.Response.Success
import com.mercadolibre.android.cardform.base.resolveRetrofitResponse
import com.mercadolibre.android.cardform.data.model.body.*
import com.mercadolibre.android.cardform.data.model.body.AssociatedCardBody
import com.mercadolibre.android.cardform.data.model.body.PaymentMethodBody
import com.mercadolibre.android.cardform.data.service.CardAssociationService
import com.mercadolibre.android.cardform.domain.AssociateCardParam
import com.mercadolibre.android.cardform.domain.AssociatedCardBM
import com.mercadolibre.android.cardform.domain.CardAssociationRepository
import kotlinx.coroutines.withContext

internal class CardAssociationRepositoryImpl(
    private val associationService: CardAssociationService,
    private val acceptThirdPartyCard: Boolean,
    private val activateCard: Boolean,
    private val contextProvider: CoroutineContextProvider = CoroutineContextProvider()
) : CardAssociationRepository {

    override suspend fun associateCard(param: AssociateCardParam) =
        withContext(contextProvider.IO) {
            runCatching {
                associationService.associateCard(
                    AssociatedCardBody(
                        param.cardTokenId,
                        PaymentMethodBody(
                            param.paymentMethodId,
                            param.paymentMethodType
                        ),
                        IssuerBody(param.issuerId.toString()),
                        Features(
                            acceptThirdPartyCard,
                            activateCard
                        )
                    )
                ).resolveRetrofitResponse().let {
                    AssociatedCardBM(it.id, it.enrollmentSuggested)
                }
            }.fold(::Success, ::Failure)
        }
}
