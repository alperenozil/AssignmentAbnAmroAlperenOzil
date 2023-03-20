package alperen.ozil.assignmentabnamro

import alperen.ozil.assignmentabnamro.databinding.RecyclerItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class PagedRecyclerAdapter : PagingDataAdapter<GithubRepo,
        PagedRecyclerAdapter.GithubRepoViewHolder>(diffCallback) {


    inner class GithubRepoViewHolder(
        val binding: RecyclerItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<GithubRepo>() {
            override fun areItemsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubRepoViewHolder {
        return GithubRepoViewHolder(
            RecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: GithubRepoViewHolder, position: Int) {
        val currentRepo = getItem(position)

        holder.binding.apply {
            holder.itemView.apply {
                textView.text = "${currentRepo?.name}"
                val imageLink = currentRepo?.owner?.avatar_url
                /*imageView.load(imageLink) {
                    crossfade(true)
                    crossfade(1000)
                }*/
            }
        }

        holder.itemView.setOnClickListener{
            val bundle = bundleOf("id" to "alperen")
            findNavController(holder.itemView).navigate(
                R.id.action_githubReposListFragment_to_githubRepoDetailsFragment,
                bundle
            )
        }


    }
}