package com.kopring.boilerplate.common.config.annotaions


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class NoLogging(val value: String = "")