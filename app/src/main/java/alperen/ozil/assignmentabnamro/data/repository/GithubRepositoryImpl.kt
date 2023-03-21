package alperen.ozil.assignmentabnamro.data.repository

import alperen.ozil.assignmentabnamro.domain.repository.GithubRepository
import alperen.ozil.assignmentabnamro.data.model.GithubRepo
import alperen.ozil.assignmentabnamro.data.repository.data_source.ReposLocalDataSource
import alperen.ozil.assignmentabnamro.data.repository.data_source.ReposRemoteDataSource

class GithubRepositoryImpl(
    private val reposRemoteDataSource: ReposRemoteDataSource,
    private val reposLocalDataSource: ReposLocalDataSource
    ) : GithubRepository {

    override suspend fun getRepos(): List<GithubRepo> {
        return reposLocalDataSource.getRepos()
    }

    override suspend fun addRepo(repo: GithubRepo) {
        reposLocalDataSource.addRepo(repo)
    }

    override suspend fun getReposFromRemote(page: Int, per_page: Int): ArrayList<GithubRepo> {
        return reposRemoteDataSource.getReposFromRemote(page,per_page)
    }

}