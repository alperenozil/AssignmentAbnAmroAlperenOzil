package alperen.ozil.assignmentabnamro

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [GithubRepo::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class GithubRepoDatabase : RoomDatabase() {

    abstract val noteDao: GithubRepoDao

    companion object {
        const val DATABASE_NAME = "repos_db"
    }
}