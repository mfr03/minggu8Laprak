package com.example.minggu8laprak

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import java.util.regex.Pattern

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener : FragmentRegisterListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        val registerButton = view.findViewById<Button>(R.id.register_button)
        val inputUsername = view.findViewById<TextInputEditText>(R.id.input_username)
        val inputPassword = view.findViewById<TextInputEditText>(R.id.input_password)
        val inputEmail = view.findViewById<TextInputEditText>(R.id.input_email)
        val inputPhone = view.findViewById<TextInputEditText>(R.id.input_phone_num)

        val termsConditions = view.findViewById<TextView>(R.id.terms_and_conditions)
        spannableClick(termsConditions, "Terms")
        spannableClick(termsConditions, "Conditions")
        val alreadyAccount = view.findViewById<TextView>(R.id.already_account)
        spannableClick(alreadyAccount, "Login")

        registerButton.setOnClickListener {
            if(inputUsername.text.toString().isNotEmpty() && inputPassword.text.toString().isNotEmpty()
                && inputEmail.text.toString().isNotEmpty() && inputPhone.text.toString().isNotEmpty()) {
                val emailRegex = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
                val phoneRegex = Pattern.compile("^(\\+62|0)[0-9]{9,11}$")
                if(!emailRegex.matcher(inputEmail.text.toString()).matches()) {
                    Snackbar.make(view, "Email is not valid", Snackbar.LENGTH_SHORT).setAnchorView(alreadyAccount).show()
                } else if(!phoneRegex.matcher(inputPhone.text.toString()).matches()) {
                    Snackbar.make(view, "Phone number is not valid", Snackbar.LENGTH_SHORT).setAnchorView(alreadyAccount).show()
                } else {
                    listener?.onInformationReceived(inputUsername.text.toString(), inputPassword.text.toString())
                }
            } else {
                Snackbar.make(view, "Mohon isi bagan yang kosong", Snackbar.LENGTH_SHORT).setAnchorView(alreadyAccount).show()
            }




        }
        inputPhone.setOnFocusChangeListener { view, b ->
            if(b) {
                inputPhone.setHint("Phone: (+62)xxxxxxxxxxx")
            } else {
                inputPhone.setHint("")
            }
        }
        return view
    }

    private fun spannableClick(tv: TextView, clickString: String) {
        val spannableString = SpannableString(tv.text)
        val clickableSpan = object : ClickableSpan(){
            override fun onClick(widget: View) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ")
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
            }
        }
        val int1 = tv.text.toString().indexOf(clickString)
        val int2 = int1 + clickString.length
        spannableString.setSpan(clickableSpan, int1, int2, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)

        tv.text = spannableString
        tv.movementMethod = LinkMovementMethod.getInstance()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentRegisterListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement FragmentListener")
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}