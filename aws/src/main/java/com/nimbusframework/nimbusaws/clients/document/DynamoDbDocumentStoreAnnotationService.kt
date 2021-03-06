package com.nimbusframework.nimbusaws.clients.document

import com.nimbusframework.nimbusaws.annotation.annotations.document.DynamoDbDocumentStore
import com.nimbusframework.nimbuscore.exceptions.InvalidStageException

object DynamoDbDocumentStoreAnnotationService {

    fun <T> getTableName(clazz: Class<T>, stage: String): String {
        val documentStoreAnnotations = clazz.getAnnotationsByType(DynamoDbDocumentStore::class.java)
        // Attempt to find specific annotation for this stage. If none exist then there is one annotation that has no stage (so uses the defaults)
        for (documentStoreAnnotation in documentStoreAnnotations) {
            if (documentStoreAnnotation.stages.contains(stage)) {
                val name = if (documentStoreAnnotation.tableName != "") documentStoreAnnotation.tableName else clazz.simpleName
                return "$name$stage"
            }
        }
        val documentStoreAnnotation = documentStoreAnnotations.firstOrNull { it.stages.isEmpty() } ?: throw InvalidStageException()
        val name = if (documentStoreAnnotation.tableName != "") documentStoreAnnotation.tableName else clazz.simpleName
        return "$name$stage"
    }

}