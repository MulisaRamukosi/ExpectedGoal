package dto

import java.util.Date

data class MatchDto(
    val date: Date,
    var location: LocationDto,
    var homeTeam: TeamDto,
    var awayTeam: TeamDto,
    val homeScore: Int,
    val awayScore: Int,
    var stats: List<StatDto>,
    var commentary: List<CommentaryDto>,
    var homeLineUp: List<LineUpDto>,
    var awayLineUp: List<LineUpDto>,
    var homeEvents: List<EventDto>,
    var awayEvents: List<EventDto>
)