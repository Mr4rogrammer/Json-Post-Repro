package info.mrprogrammer.corutinetest.framework

import info.mrprogrammer.corutinetest.domain.model.PostDataModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    fun getPost(): Call<List<PostDataModel>>
}
