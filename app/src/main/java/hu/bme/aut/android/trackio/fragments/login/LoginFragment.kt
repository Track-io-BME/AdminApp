package hu.bme.aut.android.trackio.fragments.login

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
import hu.bme.aut.android.trackio.model.SharedPrefConfig
import hu.bme.aut.android.trackio.network.InternetConnectivityChecker
import hu.bme.aut.android.trackio.network.data.Login
import hu.bme.aut.android.trackio.viewmodell.LoginFragmentViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

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

        var login = SharedPrefConfig.getBoolean("pref_loggedin", false)
        if (login) {
            if (InternetConnectivityChecker.isOnline()) {
                viewModel.login(
                    Login(
                        SharedPrefConfig.getString("pref_email", "nomail"),
                        SharedPrefConfig.getString("pref_password", "password")
                    )
                ).observe(viewLifecycleOwner) { succesfulLogin ->
                    if (succesfulLogin) {
                        SharedPrefConfig.put("pref_loggedin", true)
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    } else {
                        Toast.makeText(context, "Wrong password or email", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {
                Toast.makeText(
                    context,
                    "No internet",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.btnLoginToHome.setOnClickListener {
            if (InternetConnectivityChecker.isOnline()) {
                viewModel.login(
                    Login(
                        binding.emailedittext.text.toString(),
                        binding.passwordtext.text.toString()
                    )
                ).observe(viewLifecycleOwner) { succesfulLogin ->
                    if (succesfulLogin) {
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    } else {
                        Toast.makeText(context, "Wrong password or email", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {
                Toast.makeText(
                    context,
                    "No internet",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)


            }
        }

    }

}
