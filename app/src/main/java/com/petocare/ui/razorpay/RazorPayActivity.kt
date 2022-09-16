package com.petocare.ui.razorpay

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import com.petocare.R
import com.petocare.databinding.ActivityRazorPayBinding
import com.petocare.infra.rootUtils.LogUtils
import com.petocare.infra.base_class.BaseActivity
import com.trimz.service.ui.confirmation.ConfirmationActivity
import org.json.JSONObject

class RazorPayActivity : BaseActivity<ActivityRazorPayBinding, RazorPayViewModel>(), PaymentResultListener {

    val TAG = this::class.java.simpleName

    var razorpayKeyID = "rzp_test_rpnbLHdt0RLqsY"
    var orderID = "order_IDSHyLNzZqdsWt"


    override fun getLayout(): Int {
        return R.layout.activity_razor_pay
    }

    override fun getViewModelClass(): Class<RazorPayViewModel> {
        return RazorPayViewModel::class.java
    }

    override fun onCreateView() {
        LogUtils.error(TAG, "onCreate")

        val extras = intent.extras
        if(extras!=null){
            razorpayKeyID = extras.getString("razorPayKey")!!
            orderID = extras.getString("orderID")!!
            LogUtils.error(TAG, "razorpayKeyID >> "+razorpayKeyID)
            LogUtils.error(TAG, "orderID >> "+orderID)
        }

        Checkout.preload(applicationContext)

        startPayment()
    }


    private fun startPayment() {
        LogUtils.error(TAG, "startPayment")
        /*
        *  You need to pass current activity in order to let Razorpay create CheckoutActivity
        * */
        val activity: Activity = this
        val checkout = Checkout()
        try{
            checkout.setKeyID(razorpayKeyID)
            checkout.setImage(R.mipmap.ic_launcher)
        }catch (e: java.lang.Exception){
            LogUtils.error(TAG, e.localizedMessage)
        }

        try {
            val options = JSONObject()
            options.put("name","Trimz")
            options.put("description","Service Charges")
            options.put("order_id", orderID);

            options.put("theme.color", "#3399cc");

            //Used to auto-read OTP for cards and net banking pages
            options.put("send_sms_hash",true)

            val prefill = JSONObject()
            prefill.put("email", "suhassasuke4090@gmail.com")
            prefill.put("contact", "+919738084090")

            options.put("prefill",prefill)
            checkout.open(activity,options)
        }catch (e: Exception){
            Toast.makeText(activity,"Error in payment: "+ e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(razorPayPaymentID: String?) {
        LogUtils.error(TAG, "OnPaymentSuccess "+ razorPayPaymentID)
        Toast.makeText(this,"onPaymentSuccess: "+ razorPayPaymentID, Toast.LENGTH_LONG).show()
        val intent = Intent(this, ConfirmationActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onPaymentError(code: Int, response: String?) {
        LogUtils.error(TAG, "onPaymentError "+ code)
        Toast.makeText(this,"onPaymentError: "+ response, Toast.LENGTH_LONG).show()
    }



}