package alperen.ozil.assignmentabnamro.domain.usecase

import alperen.ozil.assignmentabnamro.data.model.GithubRepo
import alperen.ozil.assignmentabnamro.domain.repository.GithubRepository

class GetReposUseCase(private val repository: GithubRepository) {
    suspend operator fun invoke(): List<GithubRepo> {
        return repository.getRepos()
    }
}
