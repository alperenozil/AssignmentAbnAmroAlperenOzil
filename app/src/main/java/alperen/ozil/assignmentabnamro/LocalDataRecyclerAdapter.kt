package alperen.ozil.assignmentabnamro

import alperen.ozil.assignmentabnamro.databinding.RecyclerItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

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
                textView.text = currentRepo.name
                val imageLink = currentRepo.owner!!.avatar_url
                /*imageView.load(imageLink) {
                    crossfade(true)
                    crossfade(1000)
                }*/
            }
        }

        holder.itemView.setOnClickListener{
            val bundle = bundleOf("id" to "alperen")
            Navigation.findNavController(holder.itemView).navigate(
                R.id.action_githubReposListFragment_to_githubRepoDetailsFragment,
                bundle
            )
        }
    }

}
