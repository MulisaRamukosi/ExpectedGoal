package entities

import constants.CardType

abstract class Event {
    abstract var id: String
    abstract var matchId: String
    abstract var minute: Int
    abstract var affectedPlayer: Int
}

data class Card(
    val type: CardType,
    override var id: String,
    override var matchId: String,
    override var minute: Int,
    override var affectedPlayer: Int
) : Event()

data class Injury(
    override var id: String,
    override var matchId: String,
    override var minute: Int,
    override var affectedPlayer: Int
) : Event()

data class Substitution(
    val inPlayer: Player,
    override var id: String,
    override var matchId: String,
    override var minute: Int,
    override var affectedPlayer: Int
) : Event()

data class Goal(
    override var id: String,
    override var matchId: String,
    override var minute: Int,
    override var affectedPlayer: Int
) : Event()
