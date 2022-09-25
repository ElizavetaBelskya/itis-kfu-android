package ru.kpfu.itis.hometask2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.kpfu.itis.hometask2.databinding.FragmentRegistrationBinding
import java.util.regex.Pattern


class RegistrationFragment : Fragment() {

    private var binding: FragmentRegistrationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        binding?.apply {
            etPhoneNum.addTextChangedListener( MyTextWatcher(etPhoneNum, etPass))
            etPass.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    var regexPass = "[\\S]{5,}"
                    btnGoToChangeableFragment.isEnabled = Pattern.matches(regexPass, etPass.text)
                }

                override fun afterTextChanged(p0: Editable?) {
                }
            })

            btnGoToChangeableFragment.setOnClickListener {

                parentFragmentManager.beginTransaction().replace(
                    MainActivity.fragmentsContainerId,
                    ChangeableFragment.getInstance(),
                    ChangeableFragment.CHANGEABLE_FRAGMENT_TAG
                ).addToBackStack(null).commit()



            }
        }

        return binding?.root
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


    companion object {
        const val REGISTRATION_FRAGMENT_TAG = "REGISTRATION_FRAGMENT_TAG"
        @JvmStatic
        fun getInstance() = RegistrationFragment()
    }


}
