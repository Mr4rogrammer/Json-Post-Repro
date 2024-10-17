package info.mrprogrammer.corutinetest.domain.repository

import info.mrprogrammer.corutinetest.domain.model.PostDataModel

interface PostRepository {
    suspend fun getListOfPost(): Result<List<PostDataModel>>
}