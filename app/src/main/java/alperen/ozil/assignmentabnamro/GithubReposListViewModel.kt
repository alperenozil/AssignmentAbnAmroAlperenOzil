package alperen.ozil.assignmentabnamro

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubReposListViewModel @Inject constructor(
    private val repoUseCases: RepoUseCases
) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 5)) {
        GithubReposPagingSource(repoUseCases)
    }.flow.cachedIn(viewModelScope)

    private val _dataFromDatabase = MutableLiveData<ArrayList<GithubRepo>>()
    val dataFromDatabase: LiveData<ArrayList<GithubRepo>> = _dataFromDatabase

    fun loadDataFromDatabase() {
        viewModelScope.launch {
            try {
                _dataFromDatabase.postValue(repoUseCases.getRepos.invoke() as ArrayList<GithubRepo>?)
            } catch (e: Exception) {

            }
        }
    }
}