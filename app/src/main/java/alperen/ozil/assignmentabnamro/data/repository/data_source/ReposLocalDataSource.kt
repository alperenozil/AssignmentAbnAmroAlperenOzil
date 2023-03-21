package alperen.ozil.assignmentabnamro.data.repository.data_source

import alperen.ozil.assignmentabnamro.data.model.GithubRepo

interface ReposLocalDataSource {
    suspend fun getRepos(): List<GithubRepo>
    suspend fun addRepo(repo: GithubRepo)
}