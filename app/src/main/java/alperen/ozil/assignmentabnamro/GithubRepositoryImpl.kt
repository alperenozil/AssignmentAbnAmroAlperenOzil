package alperen.ozil.assignmentabnamro

import retrofit2.Response

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

    private fun responseToResource(response: Response<GithubRepo>): Resource<GithubRepo> {
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

}