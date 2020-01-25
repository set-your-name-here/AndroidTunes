package myself.synh.androidtunes

import android.app.Application
import myself.synh.androidtunes.models.retrofit.RetrofitController

class App : Application(){

    companion object{
        lateinit var retrofit: RetrofitController
    }

    override fun onCreate() {
        super.onCreate()
        retrofit = RetrofitController.create()
    }
}