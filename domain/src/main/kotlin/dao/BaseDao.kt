package dao

import dao.rules.Rule

interface BaseDao<T> {
    fun insert(entity: T)
    fun getById(id: String): T
    fun getByRule(rule: Rule<T>): List<T>
}