package dao.rules

interface Rule<T> {
    fun buildRule(entity: T): String
}