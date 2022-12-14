package hu.bme.aut.android.trackio.fragments.addnewchallenge

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.trackio.R
import hu.bme.aut.android.trackio.model.Challenge
import hu.bme.aut.android.trackio.viewmodell.ChallengeViewModel
import hu.bme.aut.android.trackio.databinding.FragmentAddChallengeBinding
import java.text.SimpleDateFormat
import java.util.*

class AddChallengeFragment : Fragment() {
    private lateinit var binding: FragmentAddChallengeBinding
    private lateinit var challengeViewModel : ChallengeViewModel
    companion object {
        const val DATE_SELECTED_KEY = "date_selected"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddChallengeBinding.inflate(inflater, container, false)
        binding.spCategory.adapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.sports)
        )

        binding.spChallengeDuration.adapter= ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.challengeDuration)
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        challengeViewModel = ViewModelProvider(this)[ChallengeViewModel::class.java]


        binding.dateStartInput.setOnClickListener {
            findNavController().navigate(R.id.action_addChallengeFragment_to_datePickerDialogFragment)

        }


        findNavController()
            .currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<DatePickerDialogFragment.DatePickerResult>(DATE_SELECTED_KEY)
            ?.observe(viewLifecycleOwner) { result ->
                result.month++
                var currentDate =
                    result.year.toString() + "-" + result.month.toString() + "-" + result.day.toString()
                val sdf = SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())
                val date = sdf.parse(currentDate)

                binding.dateStartInput.text = currentDate
                var challengeDuration = Challenge.SportDuration.getByOrdinal(binding.spChallengeDuration.selectedItemPosition)
                var durationInLong : Long
                if(challengeDuration == Challenge.SportDuration.DAILY){
                    durationInLong = 86400000
                }
                else{
                    durationInLong = 86400000*7
                }

                binding.dateEnd.text=SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date.time+durationInLong)
            }

        binding.addButton.setOnClickListener{
            if(binding.editTextDistance.text.isEmpty() && binding.editTextDistance.text.isEmpty()){
                Toast.makeText(context,"Distance and duration field is empty",Toast.LENGTH_SHORT).show()
            }
            else if(binding.editTextDistance.text.isEmpty()){
                Toast.makeText(context,"Distance field is empty",Toast.LENGTH_SHORT).show()
            }
            else if(binding.dateEnd.text.equals(binding.dateStartInput.text)){
                Toast.makeText(context,"Please add a start date",Toast.LENGTH_SHORT).show()
            }
            else{
                insertIntoDatabase()
                findNavController().popBackStack()

            }
        }

    }

    private fun insertIntoDatabase(){
        val df = SimpleDateFormat("yyyy-MM-dd")
        val dateinLong = df.parse(binding.dateStartInput.text.toString()).time
        val newChallenge = Challenge(id=111,
            distance= binding.editTextDistance.text.toString().toFloat(),
            sportType = Challenge.SportType.getByOrdinal(binding.spCategory.selectedItemPosition)?: Challenge.SportType.WALKING,
            duration = Challenge.SportDuration.getByOrdinal(binding.spChallengeDuration.selectedItemPosition)?: Challenge.SportDuration.DAILY,
            startDate = dateinLong
        )
        challengeViewModel.postChallenge(newChallenge)
        Toast.makeText(context,"Challenge added",Toast.LENGTH_SHORT).show()
    }
}



