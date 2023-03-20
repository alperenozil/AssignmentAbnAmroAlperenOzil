package alperen.ozil.assignmentabnamro

import alperen.ozil.assignmentabnamro.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHosFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHosFragment.navController
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    /*private fun setupRecyclerView() {

        pagedRecyclerAdapter = PagedRecyclerAdapter()

        binding.recyclerAlperen.apply {
            adapter = pagedRecyclerAdapter
            layoutManager = StaggeredGridLayoutManager(
                1, StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
        }

    }

    private fun loadData() {

        lifecycleScope.launch {
            viewModel.listData.collectLatest {
                Log.d("aaa", "load: $it")
                pagedRecyclerAdapter.submitData(it)
            }

        }
    }*/
}