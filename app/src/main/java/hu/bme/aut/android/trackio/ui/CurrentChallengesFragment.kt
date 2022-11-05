package hu.bme.aut.android.trackio.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.trackio.R
import hu.bme.aut.android.trackio.data.ChallengeViewModel
import hu.bme.aut.android.trackio.databinding.FragmentCurrentChallengesBinding
import hu.bme.aut.android.trackio.list.ListAdapter

class CurrentChallengesFragment : Fragment() {
    private lateinit var binding : FragmentCurrentChallengesBinding
    private lateinit var mChallangeViewModel: ChallengeViewModel

    private lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentChallengesBinding.inflate(inflater, container, false)
        //RecycleView
 //TODO listenres
        initRecyclerView()
        mChallangeViewModel = ViewModelProvider(this)[ChallengeViewModel::class.java]
        mChallangeViewModel.readAllData.observe(viewLifecycleOwner) { challenge ->
            adapter.setData(challenge)

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_currentChallengesFragment_to_addChallengeFragment)
        }
    }

    private fun initRecyclerView() {
        adapter = ListAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
       // loadItemsInBackground()
    }
}