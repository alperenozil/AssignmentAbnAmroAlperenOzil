package alperen.ozil.assignmentabnamro

import alperen.ozil.assignmentabnamro.databinding.FragmentGithubReposListBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class GithubReposListFragment : Fragment() {
    private var _binding: FragmentGithubReposListBinding? = null
    private val binding get() = _binding
    private lateinit var pagedRecyclerAdapter: PagedRecyclerAdapter
    private lateinit var localDataRecyclerAdapter: LocalDataRecyclerAdapter
    private lateinit var connectionLiveData: ConnectionLiveData
    private val viewModel by viewModels<GithubReposListViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGithubReposListBinding.inflate(inflater, container, false)

        setupRecyclerViewForRemoteData()
        showPagedDataFromRemote()
        /*if(isOnline(requireContext())){
            Toast.makeText(requireContext(), "connected", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "not connected", Toast.LENGTH_SHORT).show()
            viewModel.loadDataFromDatabase()
            viewModel.dataFromDatabase.observe(requireActivity()){
                setupRecyclerViewForLocalData(it)
                Log.d(TAG, "reis datafromdatabase $it ")
            }
        }*/
        return binding?.root
    }

    private fun showPagedDataFromRemote() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.listData.collect {
                pagedRecyclerAdapter.submitData(it)
            }
        }
    }

    private fun setupRecyclerViewForRemoteData() {
        // remote icin PagedRecyclerAdapter. local icin baska bi adapter yap
        pagedRecyclerAdapter = PagedRecyclerAdapter()
        binding?.GithubReposRecyclerView?.apply {
            adapter = pagedRecyclerAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun setupRecyclerViewForLocalData(githubRepos: ArrayList<GithubRepo>) {
        localDataRecyclerAdapter = LocalDataRecyclerAdapter(githubRepos)
        binding?.GithubReposRecyclerView?.apply {
            adapter = localDataRecyclerAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}