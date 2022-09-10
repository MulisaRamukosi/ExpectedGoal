package models

import constants.Month

data class TargetDate(
    val day: Int = 1,
    val month: Month,
    val year: Int
)
