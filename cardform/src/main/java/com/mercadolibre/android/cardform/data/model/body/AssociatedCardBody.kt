package com.mercadolibre.android.cardform.data.model.body

internal data class AssociatedCardBody(
    val cardTokenId: String,
    val paymentMethod: PaymentMethodBody,
    val issuer: IssuerBody,
    val acceptThirdPartyCard: Boolean,
    val activateCard: Boolean,
    val flowId: String
)