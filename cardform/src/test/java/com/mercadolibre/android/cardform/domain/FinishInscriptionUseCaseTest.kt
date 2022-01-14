package com.mercadolibre.android.cardform.domain

import com.mercadolibre.android.cardform.TestContextProvider
import com.mercadolibre.android.cardform.data.mapper.FinishInscriptionBodyMapper
import com.mercadolibre.android.cardform.data.model.request.FinishInscriptionParam
import com.mercadolibre.android.cardform.data.repository.FinishInscriptionRepositoryImpl
import com.mercadolibre.android.cardform.data.service.FinishInscriptionService
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class FinishInscriptionUseCaseTest {

    @Nested
    @DisplayName("Given a user get completion record data")
    inner class GivenAUserGetCompletionRecordData {

        @Nested
        @DisplayName("When get completion record data with success")
        inner class WhenGetCompletionRecordDataWithSuccess {

            private val finishInscriptionBodyMapper = mockk<FinishInscriptionBodyMapper>(relaxed = true)
            private val finishInscriptionService = mockk<FinishInscriptionService>(relaxed = true)
            private val finishInscriptionParam = mockk<FinishInscriptionParam>(relaxed = true)
            private val contextProvider = TestContextProvider()
            private val finishInscriptionRepositoryImpl = FinishInscriptionRepositoryImpl(
                finishInscriptionBodyMapper,
                finishInscriptionService, contextProvider
            )
            private val subject = FinishInscriptionUseCase(finishInscriptionRepositoryImpl)

            @Test
            fun `Then performed successfully`() {
                runBlocking {
                    assertNotNull(subject.execute(finishInscriptionParam))
                }
            }
        }
    }
}