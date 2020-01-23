package myself.synh.androidtunes.models.retrofit

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import myself.synh.androidtunes.models.entities.Result
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitController {

    @GET("search")
    fun search(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String
    ): Observable<Result>

    companion object Factory {

        private const val BASE_URL: String = "https://itunes.apple.com/"
        const val COUNTRY_CODE_RU: String = "RU"
        const val MEDIA_TYPE: String = "music"

        fun create(): RetrofitController {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(RetrofitController::class.java)
        }
    }
}