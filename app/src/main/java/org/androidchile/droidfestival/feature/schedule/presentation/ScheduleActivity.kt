package org.androidchile.droidfestival.feature.schedule.presentation

import android.os.Bundle
import org.androidchile.droidfestival.DroidFestivalApplication
import org.androidchile.droidfestival.fundation.di.ApplicationComponent
import org.androidchile.droidfestival.fundation.platform.BaseActivity

class ScheduleActivity : BaseActivity() {

    private val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as DroidFestivalApplication).appComponent
    }

    override fun fragment() = ScheduleFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }
}