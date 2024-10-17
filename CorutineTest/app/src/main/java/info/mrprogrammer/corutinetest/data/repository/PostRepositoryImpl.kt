package info.mrprogrammer.corutinetest.data.repository

import android.util.Log
import info.mrprogrammer.corutinetest.domain.model.PostDataModel
import info.mrprogrammer.corutinetest.domain.repository.PostRepository
import info.mrprogrammer.corutinetest.framework.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class PostRepositoryImpl @Inject constructor(private val apiService: ApiService) : PostRepository {
    override suspend fun getListOfPost(): Result<List<PostDataModel>> {

        return suspendCoroutine { continuation ->
            apiService.getPost().enqueue(object : Callback<List<PostDataModel>> {
                override fun onResponse(
                    call: Call<List<PostDataModel>>,
                    response: Response<List<PostDataModel>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { posts ->
                            continuation.resume(Result.success(posts))
                        }
                    } else {
                        continuation.resume(Result.failure(Throwable("Fetching issues")))
                        Log.e("Fetch Issues", "Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<PostDataModel>>, t: Throwable) {
                    continuation.resume(Result.failure(Throwable("Fetching issues")))
                }

            })
        }
    }
}