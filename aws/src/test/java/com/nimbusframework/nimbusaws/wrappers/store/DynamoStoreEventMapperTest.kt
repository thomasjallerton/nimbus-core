package com.nimbusframework.nimbusaws.wrappers.store

import com.amazonaws.services.lambda.runtime.events.DynamodbEvent
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

class DynamoStoreEventMapperTest: AnnotationSpec() {

    val underTest = DynamoStoreEventMapper

    @Test
    fun canProcessDynamoEvent() {
        val request = DynamodbEvent.DynamodbStreamRecord()
                .withEventName("INSERT")
                .withEventID("eventId") as DynamodbEvent.DynamodbStreamRecord
        val result = underTest.getStoreEvent(request, "requestId")
        result.eventId shouldBe "eventId"
        result.eventName shouldBe "INSERT"
        result.requestId shouldBe "requestId"
    }
}