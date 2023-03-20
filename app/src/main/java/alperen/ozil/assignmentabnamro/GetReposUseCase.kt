package alperen.ozil.assignmentabnamro

class GetReposUseCase(private val repository: GithubRepository) {
    suspend operator fun invoke(): List<GithubRepo> {
        return repository.getRepos()
    }
}
