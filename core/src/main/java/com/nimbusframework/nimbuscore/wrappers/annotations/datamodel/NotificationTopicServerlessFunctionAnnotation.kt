package com.nimbusframework.nimbuscore.wrappers.annotations.datamodel

import com.nimbusframework.nimbuscore.annotations.function.NotificationServerlessFunction

class NotificationTopicServerlessFunctionAnnotation(private val notificationTopicFunctionAnnotation: NotificationServerlessFunction): DataModelAnnotation() {

    override fun internalDataModel(): Class<out Any> {
        return notificationTopicFunctionAnnotation.notificationTopic.java
    }

}