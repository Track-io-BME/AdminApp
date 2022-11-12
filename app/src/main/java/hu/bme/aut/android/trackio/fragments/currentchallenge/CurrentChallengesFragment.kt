package hu.bme.aut.android.trackio.fragments.currentchallenge

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.trackio.R
import hu.bme.aut.android.trackio.viewmodell.ChallengeViewModel
import hu.bme.aut.android.trackio.databinding.FragmentCurrentChallengesBinding
import hu.bme.aut.android.trackio.fragments.startAnimation
import hu.bme.aut.android.trackio.model.Challenge

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

        mChallangeViewModel.data()
        mChallangeViewModel.readAllData.observe(viewLifecycleOwner) { challenge ->
            adapter.setData(challenge)
        }

        /*mChallangeViewModel.responseData.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                Log.d("talan", it.body().toString())
            } else {
                Log.d("talan", "nemnyert")
            }
        }*/
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val animation = AnimationUtils.loadAnimation(context, R.anim.poppingcircle).apply {
            duration = 700
            interpolator = AccelerateDecelerateInterpolator()
        }

        binding.floatingActionButton.setOnClickListener {
            binding.floatingActionButton.isVisible = false
            binding.circle.isVisible = true
            binding.circle.startAnimation(animation) {
                context?.let { it1 -> ContextCompat.getColor(it1,R.color.primary) }
                    ?.let { it2 -> binding.root.setBackgroundColor(it2) }
                binding.recyclerView.isVisible = false
                binding.textView8.isVisible = false
                binding.circle.isVisible = false

                findNavController().navigate(R.id.action_currentChallengesFragment_to_addChallengeFragment)
            }
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