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

    /**
     * @param term - The URL-encoded text string you want to search for
     * @param country - The two-letter country code for the store you want to search.
     * The search uses the default store front for the specified country.
     * @param media - The media type you want to search for. (Not required)
     * @param entity - The type of results you want returned, relative to the specified media type.
     * For example: movieArtist for a movie media type search.
     * The default is the track entity associated with the specified media type. (Not required)
     */
    @GET("search")
    fun search(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String,
        @Query("entity") entity: String
    ): Observable<Result>

    /**
     * @param id - The identify of album
     * @param country - The two-letter country code for the store you want to search.
     * @param entity - The type of results you want returned, relative to the specified media type.
     */
    @GET("lookup")
    fun lookUp(
        @Query("id") id: Long,
        @Query("country") country: String,
        @Query("entity") entity: String
    ): Observable<Result>

    companion object Factory {

        private const val BASE_URL: String = "https://itunes.apple.com/"

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