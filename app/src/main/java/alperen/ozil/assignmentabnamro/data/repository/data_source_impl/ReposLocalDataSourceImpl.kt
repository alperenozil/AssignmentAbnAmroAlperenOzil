package alperen.ozil.assignmentabnamro.data.repository.data_source_impl

import alperen.ozil.assignmentabnamro.data.db.GithubRepoDao
import alperen.ozil.assignmentabnamro.data.model.GithubRepo
import alperen.ozil.assignmentabnamro.data.repository.data_source.ReposLocalDataSource

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