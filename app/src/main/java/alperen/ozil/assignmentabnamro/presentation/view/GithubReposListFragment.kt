package alperen.ozil.assignmentabnamro.presentation.view

import alperen.ozil.assignmentabnamro.data.model.GithubRepo
import alperen.ozil.assignmentabnamro.databinding.FragmentGithubReposListBinding
import alperen.ozil.assignmentabnamro.presentation.adapter.LocalDataRecyclerAdapter
import alperen.ozil.assignmentabnamro.presentation.adapter.PagedRecyclerAdapter
import alperen.ozil.assignmentabnamro.presentation.viewmodel.GithubReposListViewModel
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    private val viewModel by viewModels<GithubReposListViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGithubReposListBinding.inflate(inflater, container, false)
        viewModel.dataFromDatabase.observe(requireActivity()){
            setupRecyclerViewForLocalData(it)
        }
        if(isOnline(requireContext())){
            Toast.makeText(requireContext(), "connection is active. data coming from remote", Toast.LENGTH_SHORT).show()
            setupRecyclerViewForRemoteData()
            showPagedDataFromRemote()
        } else {
            Toast.makeText(requireContext(), "no connection. data coming from local", Toast.LENGTH_SHORT).show()
            viewModel.loadDataFromDatabase()
        }
        return binding?.root
    }

    private fun showPagedDataFromRemote() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.listData.collectLatest {
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

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}