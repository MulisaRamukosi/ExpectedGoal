package dao

import dao.rules.Rule
import entities.Event

internal class EventDaoImpl : EventDao{
    override fun insert(entity: Event) {
        TODO("Not yet implemented")
    }

    override fun getById(id: String): Event {
        TODO("Not yet implemented")
    }

    override fun getByRule(rule: Rule<Event>): List<Event> {
        TODO("Not yet implemented")
    }
}