package com.nimbusframework.nimbuscore.clients.store.conditions.variable

data class NumericVariable(val number: Number): ConditionVariable() {

    override fun getValue(): Any {
        return number
    }

}