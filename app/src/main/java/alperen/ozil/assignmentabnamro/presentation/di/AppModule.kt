package alperen.ozil.assignmentabnamro.presentation.di

import alperen.ozil.assignmentabnamro.data.api.util.Constants
import alperen.ozil.assignmentabnamro.data.api.GithubReposApiService
import alperen.ozil.assignmentabnamro.data.db.Converters
import alperen.ozil.assignmentabnamro.data.db.GithubRepoDao
import alperen.ozil.assignmentabnamro.data.db.GithubRepoDatabase
import alperen.ozil.assignmentabnamro.data.repository.GithubRepositoryImpl
import alperen.ozil.assignmentabnamro.data.repository.data_source.ReposLocalDataSource
import alperen.ozil.assignmentabnamro.data.repository.data_source.ReposRemoteDataSource
import alperen.ozil.assignmentabnamro.data.repository.data_source_impl.ReposLocalDataSourceImpl
import alperen.ozil.assignmentabnamro.data.repository.data_source_impl.ReposRemoteDataSourceImpl
import alperen.ozil.assignmentabnamro.domain.repository.GithubRepository
import alperen.ozil.assignmentabnamro.domain.usecase.AddRepoUseCase
import alperen.ozil.assignmentabnamro.domain.usecase.GetReposFromRemoteUseCase
import alperen.ozil.assignmentabnamro.domain.usecase.GetReposUseCase
import alperen.ozil.assignmentabnamro.domain.usecase.RepoUseCases
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
        return githubRepoDatabase.githubRepoDao
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
    ): ReposRemoteDataSource {
        return ReposRemoteDataSourceImpl(githubReposApiService)
    }

    @Singleton
    @Provides
    fun provideReposLocalDataSource(dao: GithubRepoDao): ReposLocalDataSource {
        return ReposLocalDataSourceImpl(dao)
    }
}