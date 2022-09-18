package dao

import dao.rules.Rule
import entities.Player

internal class PlayerDaoImpl : PlayerDao {
    override fun insert(entity: Player) {
        TODO("Not yet implemented")
    }

    override fun getById(id: String): Player {
        TODO("Not yet implemented")
    }

    override fun getByRule(rule: Rule<Player>): List<Player> {
        TODO("Not yet implemented")
    }
}