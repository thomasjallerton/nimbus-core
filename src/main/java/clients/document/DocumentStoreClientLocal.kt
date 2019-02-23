package clients.document

import testing.LocalNimbusDeployment

class DocumentStoreClientLocal<T>(private val clazz: Class<T>): DocumentStoreClient<T>(clazz) {

    private val localNimbusDeployment = LocalNimbusDeployment.getInstance()
    private val documentStore = localNimbusDeployment.getDocumentStore(clazz)

    override fun put(obj: T) {
        documentStore.put(obj)
    }

    override fun delete(obj: T) {
        documentStore.delete(obj)
    }

    override fun deleteKey(keyObj: Any) {
        documentStore.deleteKey(keyObj)
    }

    override fun getAll(): List<T> {
        return documentStore.getAll()
    }

    override fun get(keyObj: Any): T? {
        return documentStore.get(keyObj)
    }
}