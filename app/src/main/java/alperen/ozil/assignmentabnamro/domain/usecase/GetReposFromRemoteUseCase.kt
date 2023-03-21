package alperen.ozil.assignmentabnamro.domain.usecase

import alperen.ozil.assignmentabnamro.data.model.GithubRepo
import alperen.ozil.assignmentabnamro.domain.repository.GithubRepository

class GetReposFromRemoteUseCase(private val repository: GithubRepository) {
    suspend operator fun invoke(page: Int, per_page: Int): List<GithubRepo> {
        return repository.getReposFromRemote(page,per_page)
    }
}
