package alperen.ozil.assignmentabnamro

class GetReposFromRemoteUseCase(private val repository: GithubRepository) {
    suspend operator fun invoke(page: Int, per_page: Int): List<GithubRepo> {
        return repository.getReposFromRemote(page,per_page)
    }
}
