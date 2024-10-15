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
    @ConfigurationProperties(prefix = "spring.datasource.havit")
    fun havitDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.dormant")
    fun dormantDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Primary
    @Bean
    fun havitTransactionManager(
        @Qualifier("havitDataSource") dataSource: DataSource
    ): PlatformTransactionManager {
        return DataSourceTransactionManager(dataSource)
    }

    @Bean
    fun dormantTransactionManager(
        @Qualifier("dormantDataSource") dataSource: DataSource
    ): PlatformTransactionManager {
        return DataSourceTransactionManager(dataSource)
    }

    @Bean
    fun compositeTransactionManager(
        @Qualifier("havitTransactionManager") havitTransactionManager: PlatformTransactionManager,
        @Qualifier("dormantTransactionManager") dormantTransactionManager: PlatformTransactionManager
    ): CompositeTransactionManager {
        return CompositeTransactionManager(havitTransactionManager, dormantTransactionManager)
    }
}