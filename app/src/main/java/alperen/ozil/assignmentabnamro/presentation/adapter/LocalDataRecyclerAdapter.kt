package alperen.ozil.assignmentabnamro.presentation.adapter

import alperen.ozil.assignmentabnamro.R
import alperen.ozil.assignmentabnamro.data.model.GithubRepo
import alperen.ozil.assignmentabnamro.databinding.RecyclerItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load

class LocalDataRecyclerAdapter(private val data: List<GithubRepo>) : RecyclerView.Adapter<LocalDataRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRepo = data[position]

        holder.binding.apply {
            holder.itemView.apply {
                nameTextView.text = currentRepo.name
                visibilityTextView.text = "visibility: ${currentRepo?.visibility}"
                publicOrPrivateTextView.text = "public or private: ${currentRepo?.visibility}"
                avatarImageView.load(currentRepo?.owner?.avatar_url) {
                    crossfade(true)
                    crossfade(1000)
                }
            }
        }

        holder.itemView.setOnClickListener{
            val bundle = bundleOf("name" to currentRepo.name.toString(),
                "full_name" to currentRepo.full_name.toString(),
                "description" to currentRepo.description.toString(),
                "avatar" to currentRepo.owner?.avatar_url.toString(),
                "visibility" to currentRepo.visibility.toString(),
                "publicorprivate" to currentRepo.visibility.toString(),
                "html_url" to currentRepo.html_url.toString())
            Navigation.findNavController(holder.itemView).navigate(
                R.id.action_githubReposListFragment_to_githubRepoDetailsFragment,
                bundle
            )
        }
    }

}
