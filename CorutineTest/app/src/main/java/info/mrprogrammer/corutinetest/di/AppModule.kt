package info.mrprogrammer.corutinetest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import info.mrprogrammer.corutinetest.data.repository.PostRepositoryImpl
import info.mrprogrammer.corutinetest.domain.GetListOfPost
import info.mrprogrammer.corutinetest.domain.repository.PostRepository
import info.mrprogrammer.corutinetest.framework.ApiService
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providePostRepository(apiService: ApiService): PostRepository {
        return PostRepositoryImpl(apiService)
    }

    @Provides
    fun provideRetrofitClient(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun apiServices(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideGetPostUseCases(postRepository: PostRepository):GetListOfPost {
        return GetListOfPost(postRepository, Dispatchers.IO)
    }
}