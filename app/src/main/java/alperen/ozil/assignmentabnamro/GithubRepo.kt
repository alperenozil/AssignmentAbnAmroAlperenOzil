package alperen.ozil.assignmentabnamro

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class GithubRepo(
    @PrimaryKey val id: Int,
    val description: String?,
    val html_url: String?,
    val name: String?,
    val full_name: String?,
    val owner: Owner?,
    val visibility: String?
)