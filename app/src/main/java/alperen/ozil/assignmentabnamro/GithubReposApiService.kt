package alperen.ozil.assignmentabnamro

import retrofit2.http.GET
import retrofit2.http.Query

interface GithubReposApiService {
    @GET("repos")
    suspend fun getGithubRepos(
        @Query("page")
        page: Int,
        @Query("per_page")
        per_page: Int,
    ): ArrayList<GithubRepo>
}
