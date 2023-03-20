package alperen.ozil.assignmentabnamro

class ReposRemoteDataSourceImpl(
    private val api: GithubReposApiService
): ReposRemoteDataSource {
    override suspend fun getReposFromRemote(page: Int, per_page: Int): ArrayList<GithubRepo> {
        return api.getGithubRepos(page,per_page)
    }
}