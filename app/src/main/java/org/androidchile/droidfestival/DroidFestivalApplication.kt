package org.androidchile.droidfestival;

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import org.androidchile.droidfestival.fundation.di.ApplicationComponent
import org.androidchile.droidfestival.fundation.di.ApplicationModule
import org.androidchile.droidfestival.fundation.di.DaggerApplicationComponent

class DroidFestivalApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
        this.initializeLeakDetection()
    }

    private fun injectMembers() = appComponent.inject(this)

    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }
}
