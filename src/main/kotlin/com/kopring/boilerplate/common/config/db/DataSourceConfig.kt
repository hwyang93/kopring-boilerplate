package com.kopring.boilerplate.common.config.db

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
class DataSourceConfig {
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.first")
    fun firstDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.second")
    fun secondDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Primary
    @Bean
    fun firstDBTransactionManager(
        @Qualifier("firstDataSource") dataSource: DataSource
    ): PlatformTransactionManager {
        return DataSourceTransactionManager(dataSource)
    }

    @Bean
    fun secondDBTransactionManager(
        @Qualifier("secondDataSource") dataSource: DataSource
    ): PlatformTransactionManager {
        return DataSourceTransactionManager(dataSource)
    }

    @Bean
    fun compositeTransactionManager(
        @Qualifier("firstDBTransactionManager") firstTransactionManager: PlatformTransactionManager,
        @Qualifier("secondDBTransactionManager") secondTransactionManager: PlatformTransactionManager
    ): CompositeTransactionManager {
        return CompositeTransactionManager(firstTransactionManager, secondTransactionManager)
    }
}