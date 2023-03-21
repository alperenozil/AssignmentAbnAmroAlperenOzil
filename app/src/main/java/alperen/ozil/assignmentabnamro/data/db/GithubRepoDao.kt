package alperen.ozil.assignmentabnamro.data.db

import alperen.ozil.assignmentabnamro.data.model.GithubRepo
import androidx.room.*

@Dao
interface GithubRepoDao {

    @Query("SELECT * FROM repos")
    suspend fun getRepos(): List<GithubRepo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRepo(repo: GithubRepo)

}