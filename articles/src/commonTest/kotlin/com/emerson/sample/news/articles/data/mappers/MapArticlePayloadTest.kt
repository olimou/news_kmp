package com.emerson.sample.news.articles.data.mappers

import com.emerson.sample.news.articles.Fixture.articleModel
import com.emerson.sample.news.articles.Fixture.articlePayload
import com.emerson.sample.news.articles.Fixture.sourceModel
import com.emerson.sample.news.articles.Fixture.sourcePayload
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MapArticlePayloadTest {
    private val mapSourcePayload: MapSourcePayload = mockk()

    private val mapArticlePayload = MapArticlePayload(
        mapSourcePayload
    )

    @AfterTest
    fun tearDown() {
        confirmVerified(
            mapSourcePayload,
        )
    }

    @Test
    fun `test mapArticlePayload`() {
        every { mapSourcePayload.map(any()) } returns sourceModel

        val result = mapArticlePayload.map(articlePayload)

        assertEquals(articleModel, result)

        verify(exactly = 1) {
            mapSourcePayload.map(sourcePayload)
        }
    }
}