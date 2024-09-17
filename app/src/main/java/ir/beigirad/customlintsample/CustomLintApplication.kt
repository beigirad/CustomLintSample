package ir.beigirad.customlintsample

import android.app.Application
import timber.log.Timber

class CustomLintApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}