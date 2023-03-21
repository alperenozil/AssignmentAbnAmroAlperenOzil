package alperen.ozil.assignmentabnamro.data.db

import alperen.ozil.assignmentabnamro.data.model.GithubRepo
import alperen.ozil.assignmentabnamro.data.model.Owner
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class GithubRepoDaoTest {
    private lateinit var database : GithubRepoDatabase
    private lateinit var dao : GithubRepoDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GithubRepoDatabase::class.java
        ).addTypeConverter(Converters()).allowMainThreadQueries().build()
        dao = database.githubRepoDao
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertRepoItem() = runBlockingTest {
        val githubRepo = GithubRepo(
            id = 1,
            description = "first-desc",
            html_url = "first-url",
            name = "first-name",
            full_name = "first-full-name",
            owner = Owner("first-owner"),
            visibility = "first-visibility"
        )
        dao.addRepo(githubRepo)
        val allGithubRepoItems = dao.getRepos()
        assertThat(allGithubRepoItems).contains(githubRepo)
    }
}