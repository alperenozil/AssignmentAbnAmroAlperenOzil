package alperen.ozil.assignmentabnamro.data.db

import alperen.ozil.assignmentabnamro.data.model.GithubRepo
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [GithubRepo::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class GithubRepoDatabase : RoomDatabase() {

    abstract val githubRepoDao: GithubRepoDao

    companion object {
        const val DATABASE_NAME = "repos_db"
    }
}