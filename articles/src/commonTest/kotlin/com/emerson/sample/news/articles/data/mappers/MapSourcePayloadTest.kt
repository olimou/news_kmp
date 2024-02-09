package com.emerson.sample.news.articles.data.mappers

import com.emerson.sample.news.articles.Fixture.sourceModel
import com.emerson.sample.news.articles.Fixture.sourcePayload
import kotlin.test.Test
import kotlin.test.assertEquals

class MapSourcePayloadTest {
    private val mapSourcePayload = MapSourcePayload()

    @Test
    fun `test mapSourcePayload`() {
        val result = mapSourcePayload.map(sourcePayload)

        assertEquals(sourceModel, result)
    }
}