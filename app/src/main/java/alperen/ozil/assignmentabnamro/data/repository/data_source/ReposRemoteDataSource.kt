package alperen.ozil.assignmentabnamro.data.repository.data_source

import alperen.ozil.assignmentabnamro.data.model.GithubRepo

interface ReposRemoteDataSource {
    suspend fun getReposFromRemote(page: Int, per_page: Int): ArrayList<GithubRepo>
}