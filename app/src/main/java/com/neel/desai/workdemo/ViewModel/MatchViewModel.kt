package com.neel.desai.workdemo.ViewModel


import com.neel.desai.workdemo.model.Matches
import com.neel.desai.workdemo.model.Results
import com.neel.desai.workdemo.retrofit.IApiService
import com.neel.desai.workdemo.retrofit.RestClient
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchViewModel(ctx: Context) : ViewModel() {
   public val Result: MutableLiveData<List<Results>>? = null
    val ctx: Context = ctx

    fun fetchRepoList() {


        if (Result?.value != null && Result?.value!!.size > 0) {

        } else {
            val call: Call<Matches>? = RestClient.createServiceApp(IApiService::class.java)
                ?.getMatch("10")
            if (call != null) {
                call.enqueue(object : Callback<Matches?> {
                    override fun onResponse(
                        call: Call<Matches?>,
                        response: Response<Matches?>
                    ) {

                        if (response != null) {
                            if (response.isSuccessful()) {
                                val outputModel: Matches? = response.body()
                                if (outputModel != null && outputModel.results.size > 0) {
                                    Result?.value = outputModel.results

                                    for (data in outputModel.results) {
                                        if (data.id.value == null) {
                                            data.id.value = ""
                                        }

                                    }

                                } else {
                                    Toast.makeText(
                                        ctx, "There is no match available.",
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
    }

}