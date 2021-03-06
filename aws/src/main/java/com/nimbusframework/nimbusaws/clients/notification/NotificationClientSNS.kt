package com.nimbusframework.nimbusaws.clients.notification

import com.amazonaws.services.sns.AmazonSNS
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.inject.Inject
import com.nimbusframework.nimbuscore.clients.function.EnvironmentVariableClient
import com.nimbusframework.nimbuscore.clients.notification.NotificationClient
import com.nimbusframework.nimbuscore.clients.notification.Protocol

internal class NotificationClientSNS(topicName: String): NotificationClient {

    @Inject
    private lateinit var snsClient: AmazonSNS

    @Inject
    private lateinit var environmentVariableClient: EnvironmentVariableClient

    private val topicArn: String by lazy {  environmentVariableClient.get("SNS_TOPIC_ARN_${topicName.toUpperCase()}") ?: "" }

    private val objectMapper: ObjectMapper = ObjectMapper()

    override fun createSubscription(protocol: Protocol, endpoint: String): String {
        val result = snsClient.subscribe(topicArn, protocol.name, endpoint)
        return result.subscriptionArn
    }

    override fun notify(message: String) {
        snsClient.publish(topicArn, message)
    }

    override fun notifyJson(message: Any) {
        snsClient.publish(topicArn, objectMapper.writeValueAsString(message))
    }

    override fun deleteSubscription(subscriptionId: String) {
        snsClient.unsubscribe(subscriptionId)
    }
}