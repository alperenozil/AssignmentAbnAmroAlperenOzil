package alperen.ozil.assignmentabnamro

import alperen.ozil.assignmentabnamro.databinding.FragmentGithubRepoDetailsBinding
import alperen.ozil.assignmentabnamro.databinding.FragmentGithubReposListBinding
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import coil.load

class GithubRepoDetailsFragment : Fragment() {
    private var _binding: FragmentGithubRepoDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGithubRepoDetailsBinding.inflate(inflater, container, false)
        arguments?.let {
            binding.nameTextView.text="Name: "+it.getString("name").toString()
            binding.fullNameTextView.text="Full Name: "+it.getString("full_name").toString()
            binding.descriptionTextView.text="Description: "+it.getString("description").toString()
            binding.avatarImageView.load(it.getString("avatar").toString()) {
                crossfade(true)
                crossfade(1000)
            }
            binding.visibilityTextView.text="Visibility: "+it.getString("visibility").toString()
            binding.publicorprivateTextView.text="Public or private: "+it.getString("publicorprivate").toString()
            binding.htmlurlTextView.text=it.getString("html_url").toString()
            binding.openBrowserButton.setOnClickListener {
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse(binding.htmlurlTextView.text.toString())
                startActivity(openURL)
            }
        }
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}