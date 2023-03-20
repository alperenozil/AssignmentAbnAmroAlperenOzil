package alperen.ozil.assignmentabnamro

interface ReposLocalDataSource {
    suspend fun getRepos(): List<GithubRepo>
    suspend fun addRepo(repo: GithubRepo)
}