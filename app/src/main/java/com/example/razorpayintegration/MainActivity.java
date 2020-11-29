package com.example.razorpayintegration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    String sampleAmount = "AMOUNT";
    int Amount;
    String Name = "FULL_NAME_OF_THE_RECEIVER_AS_PER_THE_RAZORPAY_ACCOUNT";
    String PhoneNumber = "PHONE_NUMBER_OF_THE_RECEIVER_AS_PER_THE_RAZORPAY_ACCOUNT";
    String Email = "EMAIL_OF_THE_RECEIVER_AS_PER_THE_RAZORPAY_ACCOUNT";
    String Description = "DESCRIPTION";

    TextView msgEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msgEt = findViewById(R.id.messageEt);

        Amount = Math.round(Float.parseFloat(sampleAmount) * 100);

    }

    public void click(View view){
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_hrkl735nExwj6r");
        checkout.setImage(R.drawable.rzp_logo);

        JSONObject object = new JSONObject();
        try {
            object.put("name",Name);
            object.put("description",Description);
            object.put("theme.color","#0093dd");
            object.put("currency","INR");
            object.put("amount",Amount);
            object.put("prefill.contact",PhoneNumber);
            object.put("prefill.email",Email);
            checkout.open(MainActivity.this,object);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onPaymentSuccess(String s) {
        msgEt.setText("Payment ID: " + s );
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }
}