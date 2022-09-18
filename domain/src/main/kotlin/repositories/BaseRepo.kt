package repositories

interface BaseRepo<T> {
    fun save(dto: T): Boolean
}