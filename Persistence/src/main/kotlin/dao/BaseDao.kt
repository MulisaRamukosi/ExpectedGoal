package dao

interface BaseDao<T> {
    fun insert(entity: T): Boolean
}