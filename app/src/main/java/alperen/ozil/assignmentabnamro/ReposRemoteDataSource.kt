package alperen.ozil.assignmentabnamro

interface ReposRemoteDataSource {
    suspend fun getReposFromRemote(page: Int, per_page: Int): ArrayList<GithubRepo>
}