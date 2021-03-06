package com.nimbusframework.nimbuscore.wrappers.annotations.datamodel

import com.nimbusframework.nimbuscore.annotations.database.UsesRelationalDatabase

class UsesRelationalDatabaseAnnotation(private val relationalDatabase: UsesRelationalDatabase): DataModelAnnotation() {

    override fun internalDataModel(): Class<out Any> {
        return relationalDatabase.dataModel.java
    }

}