package com.neel.desai.workdemo.repo

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.neel.desai.workdemo.database.DatabaseClient
import com.neel.desai.workdemo.model.Matches
import com.neel.desai.workdemo.model.Results
import com.neel.desai.workdemo.retrofit.IApiService
import com.neel.desai.workdemo.retrofit.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchRepository(mcontext: Context) {


    val mcontext: Context = mcontext;
    var list: MutableLiveData<List<Results>> = MutableLiveData<List<Results>>()
    fun getMovies(): MutableLiveData<List<Results>> {

        /*    list.value = DatabaseClient.getInstance(mcontext)?.getAppDatabase()
                ?.taskDao()?.getAll()?.value*/
        DatabaseClient.getInstance(mcontext).getAppDatabase()
            .taskDao().getAll().observeForever { Result ->

                if (Result != null) {
                    list.value = Result
                } else {


                    val call: Call<Matches>? =
                        RestClient.createServiceApp(IApiService::class.java)?.getMatch("10")

                    call?.enqueue(object : Callback<Matches?> {
                        override fun onResponse(
                            call: Call<Matches?>,
                            response: Response<Matches?>
                        ) {

                            if (response != null) {
                                if (response.isSuccessful()) {
                                    val outputModel: Matches? = response.body()
                                    if (outputModel != null && outputModel.results.size > 0) {


                                        for (data in outputModel.results) {
                                            data.RequestFlg = ""
                                            if (data.id.value == null) {
                                                data.id.value = ""

                                            }
                                            //adding to database
                                            DatabaseClient.getInstance(mcontext)!!.getAppDatabase()
                                                ?.taskDao()
                                                ?.insert(data)
                                        }
                                        list.value = outputModel.results

                                    } else {
                                        list.value = null;
                                        Toast.makeText(
                                            mcontext, "There is no match available.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        }

                        override fun onFailure(
                            call: Call<Matches?>,
                            throwable: Throwable
                        ) {
                            throwable.printStackTrace()

                        }
                    })
                }
            }




        return list
    }

    fun updateMatch(pos: Int, movie: Results) {

        list.value?.get(pos)?.RequestFlg = movie.RequestFlg
        list.value = list.value
        DatabaseClient.getInstance(mcontext)!!.getAppDatabase()
            .taskDao()
            .update(movie)


        //getMovies()

    }

}