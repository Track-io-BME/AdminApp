package hu.bme.aut.android.trackio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.trackio.R
import hu.bme.aut.android.trackio.databinding.FragmentAddChallengeBinding
import java.text.SimpleDateFormat
import java.util.*

class AddChallengeFragment : Fragment() {
    private lateinit var binding : FragmentAddChallengeBinding

    companion object {
        const val DATE_SELECTED_KEY = "date_selected"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View {
        binding = FragmentAddChallengeBinding.inflate(inflater, container, false)

        binding.dateInput.setOnClickListener{
            findNavController().navigate(R.id.action_addChallengeFragment_to_datePickerDialogFragment)
        }

        val currentDate: String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())

        binding.dateInput.setText(currentDate)

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val sports = resources.getStringArray(R.array.sports)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,sports)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)







    }
}