package com.petocare.ui.login.screen

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.petocare.R
import com.petocare.ui.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.fragment_verify.*

class VerifyFragment : Fragment() {

    var otpStatusExpiry = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verify, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initMvvm()
    }

    private fun initView() {
        val timer = object: CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = (millisUntilFinished / 1000) % 60
                val minutes = (millisUntilFinished / (1000 * 60) % 60)
                count_down.text = minutes.toString() +":"+ seconds.toString()
            }

            override fun onFinish() {
                otpStatusExpiry = false
                login_btn.isEnabled = false
                login_btn.alpha = 0.5f
            }
        }
        timer.start()
        resend_otp.setOnClickListener {
            timer.start()
            login_btn.isEnabled = true
            login_btn.alpha = 1f
        }
        login_btn.setOnClickListener {
            startActivity(Intent(requireContext(), DashboardActivity::class.java))
            requireActivity().finish()
        }
        back_button.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initMvvm() {

    }
}