package hu.bme.aut.android.trackio.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.trackio.R
import hu.bme.aut.android.trackio.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[LoginFragmentViewModel::class.java]

        binding.btnLoginToHome.setOnClickListener {
            if(viewModel.loginUser(binding.emailedittext.text.toString(),binding.passwordtext.text.toString())){
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
            else{
                Toast.makeText(context,"Wrong password or email",Toast.LENGTH_SHORT).show()
            }
        }
    }
}