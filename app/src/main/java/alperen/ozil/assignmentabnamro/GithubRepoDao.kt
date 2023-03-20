package alperen.ozil.assignmentabnamro

import androidx.room.*

@Dao
interface GithubRepoDao {

    @Query("SELECT * FROM repos")
    suspend fun getRepos(): List<GithubRepo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRepo(repo: GithubRepo)

    @Delete
    suspend fun deleteNote(note: GithubRepo)
}