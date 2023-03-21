package alperen.ozil.assignmentabnamro.data.repository

import alperen.ozil.assignmentabnamro.data.model.GithubRepo
import alperen.ozil.assignmentabnamro.domain.usecase.RepoUseCases
import androidx.paging.PagingSource
import androidx.paging.PagingState

class GithubReposPagingSource(
    private val repoUseCases: RepoUseCases,
) : PagingSource<Int, GithubRepo>() {

    override fun getRefreshKey(state: PagingState<Int, GithubRepo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, GithubRepo> {
        return try {
            val currentPage = params.key ?: 1
            val response = repoUseCases.getReposFromRemote.invoke(currentPage, 5)
            response.forEach {
                repoUseCases.addRepo(it)
            }

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}