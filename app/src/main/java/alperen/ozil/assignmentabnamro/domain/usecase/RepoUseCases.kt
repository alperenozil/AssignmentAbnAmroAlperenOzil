package alperen.ozil.assignmentabnamro.domain.usecase

data class RepoUseCases(
    val getRepos: GetReposUseCase,
    val addRepo: AddRepoUseCase,
    val getReposFromRemote: GetReposFromRemoteUseCase
)