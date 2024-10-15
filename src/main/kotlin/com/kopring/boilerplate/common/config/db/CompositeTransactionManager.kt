package com.kopring.boilerplate.common.config.db

import org.springframework.transaction.TransactionDefinition
import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.TransactionManager
import org.springframework.transaction.PlatformTransactionManager

class CompositeTransactionManager(
    private val firstDBTransactionManager: PlatformTransactionManager,
    private val secondDBTransactionManager: PlatformTransactionManager
) : TransactionManager {
    fun getTransaction(definition: TransactionDefinition?): TransactionStatus {
        val firstDBStatus = firstDBTransactionManager.getTransaction(definition)
        val secondDBStatus = secondDBTransactionManager.getTransaction(definition)
        return CompositeTransactionStatus(firstDBStatus, secondDBStatus)
    }

    fun commit(status: TransactionStatus) {
        val compositeStatus = status as CompositeTransactionStatus
        try {
            firstDBTransactionManager.commit(compositeStatus.firstDBStatus)
            secondDBTransactionManager.commit(compositeStatus.secondDBStatus)
        } catch (ex: Exception) {
            rollback(status)
            throw ex
        }
    }

    fun rollback(status: TransactionStatus) {
        val compositeStatus = status as CompositeTransactionStatus
        try {
            firstDBTransactionManager.rollback(compositeStatus.firstDBStatus)
        } finally {
            secondDBTransactionManager.rollback(compositeStatus.secondDBStatus)
        }
    }

    private class CompositeTransactionStatus(
        val firstDBStatus: TransactionStatus,
        val secondDBStatus: TransactionStatus
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