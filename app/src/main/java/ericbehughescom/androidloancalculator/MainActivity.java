package ericbehughescom.androidloancalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView usd;
    private EditText loanAmount;
    private TextView loanTermLabel;
    private EditText loanTermValueEditText;
    private TextView interestRateTextView;
    private EditText interestRateEditText;
    private Button calculateLoanBtn;
    private TextView monthlyPaymentTextView;
    private TextView monthlyPaymentValue;
    private LoanCalculator lc;
    private TextView loanAndInterestPaid;
    private TextView interestPaidTextView;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-09-07 19:56:12 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        usd = (TextView)findViewById( R.id.usd );
        loanAmount = (EditText)findViewById( R.id.loanAmount );
        loanTermLabel = (TextView)findViewById( R.id.loanTermLabel );
        loanTermValueEditText = (EditText)findViewById( R.id.loanTermValueEditText );
        interestRateTextView = (TextView)findViewById( R.id.interestRateTextView );
        interestRateEditText = (EditText)findViewById( R.id.interestRateEditText );
        calculateLoanBtn = (Button)findViewById( R.id.calculateLoanBtn );
        monthlyPaymentTextView = (TextView)findViewById( R.id.monthlyPaymentTextView );
        monthlyPaymentValue = (TextView)findViewById( R.id.monthlyPaymentValue );
        loanAndInterestPaid = (TextView)findViewById( R.id.totalPaymentsValue );
        interestPaidTextView = (TextView)findViewById( R.id.totalInterestPaidValue);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        this.lc = new LoanCalculator();



    }

    public void calculateInterestForLoan(View v){

        // get calculated values ready
        int term = Integer.parseInt(loanTermValueEditText.getText().toString());
        double rate = Integer.parseInt(interestRateEditText.getText().toString());
        double loan = Integer.parseInt(loanAmount.getText().toString());

        //build loan calculator
        this.lc.setLoanAmount(loan);
        this.lc.setNumberOfYears(term);
        this.lc.setYearlyInterestRate(rate);

        final double monthlyPayment = Math.round(this.lc.getMonthlyPayment());
        final double interestPaid = Math.round(this.lc.getTotalCostOfLoan() - loan);
        final double loanAndInterest = Math.round(this.lc.getTotalCostOfLoan() + loan);

        //create hashmap for showInterestPaidCalculation method
        Map<String,Object> payload = new HashMap<String,Object>();

        //add values to payload to showInterestPaidCalculation
        payload.put("monthlyPayment", monthlyPayment);
        payload.put("interestPaid", interestPaid);
        payload.put("loanAndInterest", loanAndInterest);

        //update view
        showInterestPaidCalculation(payload);
    }

    private void showInterestPaidCalculation(Map<String,Object> o )
    {
        monthlyPaymentValue.setText(o.get("monthlyPayment").toString());
        interestPaidTextView.setText(o.get("interestPaid").toString());
        loanAndInterestPaid.setText(o.get("loanAndInterest").toString());
    }




}



