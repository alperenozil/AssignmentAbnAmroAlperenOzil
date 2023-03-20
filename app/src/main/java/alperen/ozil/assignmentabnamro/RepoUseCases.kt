package alperen.ozil.assignmentabnamro

data class RepoUseCases(
    val getRepos: GetReposUseCase,
    val addRepo: AddRepoUseCase,
    val getReposFromRemote: GetReposFromRemoteUseCase
)