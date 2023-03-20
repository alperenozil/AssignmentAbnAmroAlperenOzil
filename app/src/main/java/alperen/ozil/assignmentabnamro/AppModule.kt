package alperen.ozil.assignmentabnamro

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit): GithubReposApiService =
        retrofit.create(GithubReposApiService::class.java)

    @Provides
    @Singleton
    fun provideGithubRepoDatabase(app: Application): GithubRepoDatabase {
        return Room.databaseBuilder(
            app,
            GithubRepoDatabase::class.java,
            GithubRepoDatabase.DATABASE_NAME
        ).addTypeConverter(Converters())
            .build()
    }

    @Singleton
    @Provides
    fun provideGithubRepoDao(githubRepoDatabase: GithubRepoDatabase): GithubRepoDao {
        return githubRepoDatabase.noteDao
    }

    @Singleton
    @Provides
    fun provideGithubRepository(
        reposRemoteDataSource: ReposRemoteDataSource,
        reposLocalDataSource: ReposLocalDataSource
    ): GithubRepository {
        return GithubRepositoryImpl(
            reposRemoteDataSource,
            reposLocalDataSource
        )
    }

    @Provides
    @Singleton
    fun provideRepoUseCases(repository: GithubRepository): RepoUseCases {
        return RepoUseCases(
            getRepos = GetReposUseCase(repository),
            addRepo = AddRepoUseCase(repository),
            getReposFromRemote = GetReposFromRemoteUseCase(repository)
        )
    }

    @Singleton
    @Provides
    fun provideReposRemoteDataSource(
        githubReposApiService: GithubReposApiService
    ):ReposRemoteDataSource{
        return ReposRemoteDataSourceImpl(githubReposApiService)
    }

    @Singleton
    @Provides
    fun provideReposLocalDataSource(dao: GithubRepoDao):ReposLocalDataSource{
        return ReposLocalDataSourceImpl(dao)
    }
}