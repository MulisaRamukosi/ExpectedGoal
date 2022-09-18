package dao

import dao.rules.Rule
import entities.Commentary

internal class CommentaryDaoImpl : CommentaryDao {
    override fun insert(entity: Commentary) {
        TODO("Not yet implemented")
    }

    override fun getById(id: String): Commentary {
        TODO("Not yet implemented")
    }

    override fun getByRule(rule: Rule<Commentary>): List<Commentary> {
        TODO("Not yet implemented")
    }
}