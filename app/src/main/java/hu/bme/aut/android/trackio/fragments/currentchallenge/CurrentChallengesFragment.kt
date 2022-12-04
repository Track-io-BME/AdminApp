package hu.bme.aut.android.trackio.fragments.currentchallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.trackio.viewmodell.ChallengeViewModel
import hu.bme.aut.android.trackio.model.Challenge
import hu.bme.aut.android.trackio.model.SharedPrefConfig
import hu.bme.aut.android.trackio.network.InternetConnectivityChecker
import hu.bme.aut.android.trackio.databinding.FragmentCurrentChallengesBinding

class CurrentChallengesFragment : Fragment(), ListAdapter.ChallengeItemClickListener {
    private lateinit var binding: FragmentCurrentChallengesBinding
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

        mChallangeViewModel.readAllData.observe(viewLifecycleOwner) { challenge ->
            adapter.setData(challenge)
        }

        return binding.root
    }

    private fun initRecyclerView() {
        adapter = ListAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    override fun onItemRemoved(item: Challenge) {
        if (InternetConnectivityChecker.isOnline()) {
            mChallangeViewModel.deleteChallenge(item)
            mChallangeViewModel.deletenetworkChallenge(item)

        }
    }

    override fun onStart() {
        super.onStart()
        if (InternetConnectivityChecker.isOnline()) {
            mChallangeViewModel.getChallengesFromServer(
                SharedPrefConfig.getString("pref_token", "no token")
            )
        }

    }
}