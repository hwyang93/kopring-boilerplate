package com.kopring.boilerplate.common.config.db

import org.springframework.transaction.TransactionDefinition
import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.TransactionManager
import org.springframework.transaction.PlatformTransactionManager

class CompositeTransactionManager(
    private val havitTransactionManager: PlatformTransactionManager,
    private val dormantTransactionManager: PlatformTransactionManager
) : TransactionManager {
    fun getTransaction(definition: TransactionDefinition?): TransactionStatus {
        val havitStatus = havitTransactionManager.getTransaction(definition)
        val dormantStatus = dormantTransactionManager.getTransaction(definition)
        return CompositeTransactionStatus(havitStatus, dormantStatus)
    }

    fun commit(status: TransactionStatus) {
        val compositeStatus = status as CompositeTransactionStatus
        try {
            havitTransactionManager.commit(compositeStatus.havitStatus)
            dormantTransactionManager.commit(compositeStatus.dormantStatus)
        } catch (ex: Exception) {
            rollback(status)
            throw ex
        }
    }

    fun rollback(status: TransactionStatus) {
        val compositeStatus = status as CompositeTransactionStatus
        try {
            havitTransactionManager.rollback(compositeStatus.havitStatus)
        } finally {
            dormantTransactionManager.rollback(compositeStatus.dormantStatus)
        }
    }

    private class CompositeTransactionStatus(
        val havitStatus: TransactionStatus,
        val dormantStatus: TransactionStatus
    ) : TransactionStatus {
        override fun createSavepoint(): Any {
            throw UnsupportedOperationException("Savepoint is not supported in CompositeTransactionStatus")
        }

        override fun rollbackToSavepoint(savepoint: Any) {
            throw UnsupportedOperationException("Rollback to savepoint is not supported in CompositeTransactionStatus")
        }

        override fun releaseSavepoint(savepoint: Any) {
            throw UnsupportedOperationException("Release savepoint is not supported in CompositeTransactionStatus")
        }

    }
}