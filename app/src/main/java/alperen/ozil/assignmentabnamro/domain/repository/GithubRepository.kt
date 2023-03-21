package alperen.ozil.assignmentabnamro.domain.repository

import alperen.ozil.assignmentabnamro.data.model.GithubRepo

interface GithubRepository {
    suspend fun getRepos(): List<GithubRepo>
    suspend fun addRepo(repo: GithubRepo)
    suspend fun getReposFromRemote(page: Int, per_page: Int): ArrayList<GithubRepo>
}