package alperen.ozil.assignmentabnamro

class AddRepoUseCase(private val repository: GithubRepository) {
    suspend operator fun invoke(repo: GithubRepo) {
        repository.addRepo(repo = repo)
    }
}