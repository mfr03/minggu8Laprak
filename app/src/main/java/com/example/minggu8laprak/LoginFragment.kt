package com.example.minggu8laprak

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
import com.example.minggu8laprak.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val loginButton = view.findViewById<Button>(R.id.login_button)
        val inputUsername = view.findViewById<TextInputEditText>(R.id.input_username)
        val inputPassword = view.findViewById<TextInputEditText>(R.id.input_password)
        loginButton.setOnClickListener {
            if(inputUsername.text.toString() == arguments?.getString("username")
                && inputPassword.text.toString() == arguments?.getString("password")) {
                val intent = Intent(context, DashboardActivity::class.java)
                startActivity(intent)
            } else {
                Snackbar.make(view, "Username atau Password salah", Snackbar.LENGTH_LONG).show()
            }
        }
        val alreadyAccount = view.findViewById<TextView>(R.id.already_account)
        spannableClick(alreadyAccount, "Register")
        val forgotPassword = view.findViewById<TextView>(R.id.forgotPassword)
        spannableClick(forgotPassword, "Forgot Password?")
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

        }
        val int1 = tv.text.toString().indexOf(clickString)
        val int2 = int1 + clickString.length
        spannableString.setSpan(clickableSpan, int1, int2, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)

        tv.text = spannableString
        tv.movementMethod = LinkMovementMethod.getInstance()

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}