package com.syleiman.gingermoney.ui.activities.main.fragments.settings.view_commands

import com.syleiman.gingermoney.ui.common.mvvm.view_commands.ViewCommand
import org.threeten.bp.DayOfWeek

class StartSelectStartDayOfWeekCommand(
    val selectedIndex: Int,
    val daysOfWeek: List<DayOfWeek>
) : ViewCommand