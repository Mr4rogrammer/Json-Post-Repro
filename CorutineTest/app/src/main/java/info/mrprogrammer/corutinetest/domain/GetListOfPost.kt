package info.mrprogrammer.corutinetest.domain

import info.mrprogrammer.corutinetest.domain.model.PostDataModel
import info.mrprogrammer.corutinetest.domain.repository.PostRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetListOfPost @Inject constructor(
    private val postRepository: PostRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend operator fun invoke(): Result<List<PostDataModel>> {
        return withContext(dispatcher) {
            return@withContext postRepository.getListOfPost()
        }
    }
}