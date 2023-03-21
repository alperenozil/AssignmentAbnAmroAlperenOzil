package alperen.ozil.assignmentabnamro.data.repository.data_source_impl

import alperen.ozil.assignmentabnamro.data.api.GithubReposApiService
import alperen.ozil.assignmentabnamro.data.model.GithubRepo
import alperen.ozil.assignmentabnamro.data.repository.data_source.ReposRemoteDataSource

class ReposRemoteDataSourceImpl(
    private val api: GithubReposApiService
): ReposRemoteDataSource {
    override suspend fun getReposFromRemote(page: Int, per_page: Int): ArrayList<GithubRepo> {
        return api.getGithubRepos(page,per_page)
    }
}