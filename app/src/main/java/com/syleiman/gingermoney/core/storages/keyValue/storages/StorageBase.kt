package com.syleiman.gingermoney.core.storages.keyValue.storages

/** Base class for storage */
abstract class StorageBase: StorageInterface, StorageOperationsInstanceInterface {
    /** Read data from storage */
    override fun <T>read(readFunc: (StorageReadOperationsInterface) -> T): T = readFunc(createReadOperationsInstance())

    /** Update data in storage */
    override fun update(updateAction: (StorageWriteOperationsInterface) -> Unit) {
        var operationsInstance: StorageCommitOperationsInterface? = null

        try{
            operationsInstance = createWriteOperationsInstance()
            updateAction(operationsInstance)
            operationsInstance.commit()
        }
        catch(ex: Exception) {
            ex.printStackTrace()
            throw ex
        }
    }

    /** Create proxy for read */
    abstract override fun createReadOperationsInstance(): StorageReadOperationsInterface

    /** Create proxy for read */
    abstract override fun createWriteOperationsInstance(): StorageCommitOperationsInterface
}