package org.koin.core.instance

import org.koin.core.bean.BeanDefinition
import org.koin.core.parameter.ParametersDefinition

class SingleInstance<T>(private val beanDefinition: BeanDefinition<T>) : Instance<T> {

    override fun isAlreadyCreated(): Boolean = (value != null)

    var value: T? = null

    override fun release() {}

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(parameters: ParametersDefinition?): T {
        if (value == null) {
            value = create(beanDefinition,parameters)
        }
        return value as? T ?: error("Single instance created couldn't return value")
    }
}