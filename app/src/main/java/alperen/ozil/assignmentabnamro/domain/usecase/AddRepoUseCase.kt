package alperen.ozil.assignmentabnamro.domain.usecase

import alperen.ozil.assignmentabnamro.data.model.GithubRepo
import alperen.ozil.assignmentabnamro.domain.repository.GithubRepository

class AddRepoUseCase(private val repository: GithubRepository) {
    suspend operator fun invoke(repo: GithubRepo) {
        repository.addRepo(repo = repo)
    }
}