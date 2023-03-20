package alperen.ozil.assignmentabnamro

interface GithubRepository {
    suspend fun getRepos(): List<GithubRepo>
    suspend fun addRepo(repo: GithubRepo)
    suspend fun getReposFromRemote(page: Int, per_page: Int): ArrayList<GithubRepo>
}