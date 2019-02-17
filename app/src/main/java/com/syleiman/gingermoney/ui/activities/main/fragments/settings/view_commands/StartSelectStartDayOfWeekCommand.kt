package com.syleiman.gingermoney.ui.activities.main.fragments.settings.view_commands

import com.syleiman.gingermoney.ui.common.view_commands.ViewCommand
import org.threeten.bp.DayOfWeek

/**
 *
 */
data class StartSelectStartDayOfWeekCommand(
    val selectedIndex: Int,
    val daysOfWeek: List<DayOfWeek>
) : ViewCommand