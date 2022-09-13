package models

import constants.BrowserType

data class Browser (
    val browserType: BrowserType,
    val openInMaximizeView: Boolean,
    val waitDurationInSeconds: Long
)