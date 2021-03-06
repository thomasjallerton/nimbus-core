package com.nimbusframework.nimbuscore.wrappers.annotations.datamodel

import com.nimbusframework.nimbuscore.annotations.document.UsesDocumentStore

class UsesDocumentStoreAnnotation(private val usesDocumentStore: UsesDocumentStore): DataModelAnnotation() {

    override fun internalDataModel(): Class<out Any> {
        return usesDocumentStore.dataModel.java
    }

}