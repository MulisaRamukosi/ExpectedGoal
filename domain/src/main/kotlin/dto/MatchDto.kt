package dto

import java.util.Date

data class MatchDto(
    val date: Date,
    val location: LocationDto,
    val homeTeam: TeamDto,
    val awayTeam: TeamDto,
    val resultDto: ResultDto,
    val stats: List<StatDto>,
    val commentary: List<CommentaryDto>,
    val homeLineUp: List<LineUpDto>,
    val awayLineUp: List<LineUpDto>,
    val homeEvents: List<EventDto>,
    val awayEvents: List<EventDto>
)