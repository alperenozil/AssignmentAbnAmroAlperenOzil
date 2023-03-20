package alperen.ozil.assignmentabnamro

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class GithubRepoDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            val noteId = it.getString("id")
            Log.d("reis details id", noteId.toString())
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_github_repo_details, container, false)
    }

}