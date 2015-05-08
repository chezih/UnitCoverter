package com.chamud.cheziandsima.unitcoverter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private Spinner unitTypeSpinner;

    private EditText amountEditText;
    private double Amount;
    private Quantity.Unit selectedFromUnit;


    TextView teaspoonTextView, tablespoonTextView, cupTextView, ounceTextView,
            pintTextView, quartTextView, gallonTextView, poundTextView,
            milliliterTextView, literTextView, milligramTextView, kilogramTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fills the spinner with the unit options
        addItemsToUnitTypeSpinner();

        // Add listener to the Spinner
        addListenerToUnitTypeSpinner();

        // Get a reference to the edit text view to retrieve the amount of the unit type
        amountEditText = (EditText) findViewById(R.id.amount_edit_text);

        Amount = Double.parseDouble(amountEditText.getText().toString());

        initializeTextViews();

        updateAllTextView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onCalcClick(View view) {

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        if (amountEditText.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, R.string.error_message, Toast.LENGTH_SHORT).show();
        } else {
            Amount = Double.parseDouble(amountEditText.getText().toString());
            updateAllTextView();
        }

    }


    public void addListenerToUnitTypeSpinner() {
        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);

        String itemSelectedInSpinner = unitTypeSpinner.getSelectedItem().toString();

        selectedFromUnit = getSelectedFromUnitFromSpinner(itemSelectedInSpinner);
        unitTypeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3) {
                // Get the item selected in the Spinner
                String itemSelectedInSpinner = parent.getItemAtPosition(pos).toString();
                selectedFromUnit = getSelectedFromUnitFromSpinner(itemSelectedInSpinner);
                updateAllTextView();
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO maybe add something here later
            }
        });
    }

    public void initializeTextViews() {

        teaspoonTextView = (TextView) findViewById(R.id.tsp_text_view);
        tablespoonTextView = (TextView) findViewById(R.id.tbs_text_view);
        cupTextView = (TextView) findViewById(R.id.cup_text_view);
        ounceTextView = (TextView) findViewById(R.id.oz_text_view);
        pintTextView = (TextView) findViewById(R.id.pint_text_view);
        quartTextView = (TextView) findViewById(R.id.quart_text_view);
        gallonTextView = (TextView) findViewById(R.id.gallon_text_view);
        poundTextView = (TextView) findViewById(R.id.pound_text_view);
        milliliterTextView = (TextView) findViewById(R.id.ml_text_view);
        literTextView = (TextView) findViewById(R.id.liter_text_view);
        milligramTextView = (TextView) findViewById(R.id.mg_text_view);
        kilogramTextView = (TextView) findViewById(R.id.kg_text_view);


    }

    public void addItemsToUnitTypeSpinner() {

        // Get a reference to the spinner
        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> unitTypeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.conversion_types, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        unitTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        unitTypeSpinner.setAdapter(unitTypeSpinnerAdapter);

    }


    public void updateAllTextView() {

        Quantity q = new Quantity(selectedFromUnit, Quantity.Unit.tsp, Amount, this);
        String newVal = q.getCalculateAmount();
        teaspoonTextView.setText(newVal);

        q = new Quantity(selectedFromUnit, Quantity.Unit.tbs, Amount, this);
        newVal = q.getCalculateAmount();
        tablespoonTextView.setText(newVal);

        q = new Quantity(selectedFromUnit, Quantity.Unit.cup, Amount, this);
        newVal = q.getCalculateAmount();
        cupTextView.setText(newVal);

        q = new Quantity(selectedFromUnit, Quantity.Unit.oz, Amount, this);
        newVal = q.getCalculateAmount();
        ounceTextView.setText(newVal);

        q = new Quantity(selectedFromUnit, Quantity.Unit.pint, Amount, this);
        newVal = q.getCalculateAmount();
        pintTextView.setText(newVal);

        q = new Quantity(selectedFromUnit, Quantity.Unit.quart, Amount, this);
        newVal = q.getCalculateAmount();
        quartTextView.setText(newVal);

        q = new Quantity(selectedFromUnit, Quantity.Unit.gallon, Amount, this);
        newVal = q.getCalculateAmount();
        gallonTextView.setText(newVal);

        q = new Quantity(selectedFromUnit, Quantity.Unit.pound, Amount, this);
        newVal = q.getCalculateAmount();
        poundTextView.setText(newVal);

        q = new Quantity(selectedFromUnit, Quantity.Unit.ml, Amount, this);
        newVal = q.getCalculateAmount();
        milliliterTextView.setText(newVal);

        q = new Quantity(selectedFromUnit, Quantity.Unit.liter, Amount, this);
        newVal = q.getCalculateAmount();
        literTextView.setText(newVal);

        q = new Quantity(selectedFromUnit, Quantity.Unit.mg, Amount, this);
        newVal = q.getCalculateAmount();
        milligramTextView.setText(newVal);

        q = new Quantity(selectedFromUnit, Quantity.Unit.kg, Amount, this);
        newVal = q.getCalculateAmount();
        kilogramTextView.setText(newVal);

    }

    private Quantity.Unit getSelectedFromUnitFromSpinner(String itemSelectedInSpinner) {
        String ml = getResources().getString(R.string.ml);
        String mg = getResources().getString(R.string.mg);
        String tsp = getResources().getString(R.string.tsp);
        String cup = getResources().getString(R.string.cup);
        String oz = getResources().getString(R.string.oz);
        String pint = getResources().getString(R.string.pint);
        String quart = getResources().getString(R.string.quart);
        String pound = getResources().getString(R.string.pound);
        String tablespoon = getResources().getString(R.string.tbs);
        String gallon = getResources().getString(R.string.gallon);
        String liter = getResources().getString(R.string.liter);
        String kilogram = getResources().getString(R.string.kg);

        if (itemSelectedInSpinner.equals(tsp)) {
            return Quantity.Unit.tsp;
        } else if (itemSelectedInSpinner.equals(tablespoon)) {
            return Quantity.Unit.tbs;
        } else if (itemSelectedInSpinner.equals(cup)) {
            return Quantity.Unit.cup;

        } else if (itemSelectedInSpinner.equals(oz)) {
            return Quantity.Unit.oz;

        } else if (itemSelectedInSpinner.equals(pint)) {
            return Quantity.Unit.pint;

        } else if (itemSelectedInSpinner.equals(quart)) {
            return Quantity.Unit.quart;

        } else if (itemSelectedInSpinner.equals(gallon)) {
            return Quantity.Unit.gallon;

        } else if (itemSelectedInSpinner.equals(pound)) {
            return Quantity.Unit.pound;

        } else if (itemSelectedInSpinner.equals(ml)) {
            return Quantity.Unit.ml;

        } else if (itemSelectedInSpinner.equals(liter)) {
            return Quantity.Unit.liter;

        } else if (itemSelectedInSpinner.equals(mg)) {
            return Quantity.Unit.mg;

        } else if (itemSelectedInSpinner.equals(kilogram)) {
            return Quantity.Unit.kg;
        } else {
            return Quantity.Unit.tsp;
        }
    }
}
