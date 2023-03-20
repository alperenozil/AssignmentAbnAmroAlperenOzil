package alperen.ozil.assignmentabnamro

class ReposLocalDataSourceImpl(
    private val dao: GithubRepoDao
): ReposLocalDataSource {
    override suspend fun getRepos(): List<GithubRepo> {
        return dao.getRepos()
    }

    override suspend fun addRepo(repo: GithubRepo) {
        return dao.addRepo(repo)
    }
}