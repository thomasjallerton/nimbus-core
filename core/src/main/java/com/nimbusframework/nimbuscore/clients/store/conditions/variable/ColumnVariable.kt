package com.nimbusframework.nimbuscore.clients.store.conditions.variable

class ColumnVariable(val fieldName: String): ConditionVariable() {

    override fun getValue(): Any {
        return fieldName
    }

}