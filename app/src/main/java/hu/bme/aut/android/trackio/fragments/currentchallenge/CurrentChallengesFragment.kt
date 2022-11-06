package hu.bme.aut.android.trackio.fragments.currentchallenge

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.trackio.R
import hu.bme.aut.android.trackio.viewmodell.ChallengeViewModel
import hu.bme.aut.android.trackio.databinding.FragmentCurrentChallengesBinding
import hu.bme.aut.android.trackio.model.Challenge

class CurrentChallengesFragment : Fragment(), ListAdapter.ChallengeItemClickListener {
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
        initRecyclerView()
        mChallangeViewModel = ViewModelProvider(this)[ChallengeViewModel::class.java]
        mChallangeViewModel.responseData.observe(viewLifecycleOwner){
            if(it.isSuccessful){
                Log.d("talan", it.body().toString())
            }
            else{
                Log.d("talan", "nemnyert")
            }
            // mChallangeViewModel.addChallengesDbToNetwork()
        }
        mChallangeViewModel.readAllData.observe(viewLifecycleOwner) { challenge ->
            adapter.setData(challenge)
        }
        //mChallangeViewModel.getChallengesFromBackend()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_currentChallengesFragment_to_addChallengeFragment)
        }
    }

    private fun initRecyclerView() {
        adapter = ListAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    override fun onItemRemoved(challenge: Challenge) {
        mChallangeViewModel.deleteChallenge(challenge)
    }


}