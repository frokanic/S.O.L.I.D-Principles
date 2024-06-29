package dependency_inversion_principle

fun main() {

}

/** - EXAMPLE */

/** Correct implementation
 *
 *  In the example below, the ContentService does not depend on either of the
 *  repositories, but rather an interface that abstracts data storage and retrieval.
 *  This makes the code more testable and flexible.
 */

class ContentService(
    private val repository: ContentRepository
) {
    fun saveContent(content: String) {
        repository.store(content)
    }

    fun getContentById(id: String): String {
        return repository.retrieve(id)
    }
}

interface ContentRepository {
    fun store(content: String)
    fun retrieve(id: String): String
}

class DatabaseContentRepository : ContentRepository {
    override fun store(content: String) {
        println("Storing content in the database")
    }

    override fun retrieve(id: String): String {
        return "Content retrieved from the database"
    }
}

class FileContentRepository : ContentRepository {
    override fun store(content: String) {
        println("Storing content in a file")
    }

    override fun retrieve(id: String): String {
        return "Content retrieved from a file"
    }
}


/** Incorrect implementation
 *
 *  Here, ContentServiceIncorrect directly uses specific storage classes (Database and
 *  File storage) rather than relying on an abstraction. This tightly couples the service
 *  to specific implementations, making it less flexible and testable, since, for example,
 *  I cannot now create a "fake database instance", populated with dummy data of the size
 *  and type I wish.
 */

class ContentServiceIncorrect{
    private val databaseStorage = DatabaseContentRepository()
    private val fileStorage = FileContentRepository()

    fun saveContent(content: String, storageType: String) {
        when (storageType) {
            "database" -> databaseStorage.store(content)
            "file" -> fileStorage.store(content)
        }
    }

    fun getContentById(id: String, storageType: String): String {
        return when (storageType) {
            "database" -> databaseStorage.retrieve(id)
            "file" -> fileStorage.retrieve(id)
            else -> "Invalid storage type"
        }
    }
}