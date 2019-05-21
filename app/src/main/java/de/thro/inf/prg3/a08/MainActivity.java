package de.thro.inf.prg3.a08;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.thro.inf.prg3.a08.api.OpenMensaAPI;
import de.thro.inf.prg3.a08.api.OpenMensaAPIService;
import de.thro.inf.prg3.a08.filtering.FilterFactory;
import de.thro.inf.prg3.a08.model.Meal;
import de.thro.inf.prg3.a08.utils.MealsFilterUtility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private final SimpleDateFormat dateFormat;
    private final OpenMensaAPI openMensaAPI;
    private ArrayAdapter<Meal> mealsArrayAdapter;
    private ArrayAdapter<CharSequence> spinnerAdapter;

    public MainActivity() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        /* TODO extract me into a singleton...please :) */
        OpenMensaAPIService svc = OpenMensaAPIService.getInstance();
        openMensaAPI = svc.getApi();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // this will inflate the layout from res/layout/activity_main.xml
        setContentView(R.layout.activity_main);


        /* create the ArrayAdapter without an given list instance */
        mealsArrayAdapter = new ArrayAdapter<>(
                this,
                R.layout.meal_entry
        );

        /* create an ArrayAdapter to fill the spinner */
        spinnerAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.meals_filter_items,
                R.layout.support_simple_spinner_dropdown_item
        );

        /* 'caching' the reference to the ListView */
        final ListView mealsListView = findViewById(R.id.mealsList);
        mealsListView.setAdapter(mealsArrayAdapter);

        final Spinner filterSpinner = findViewById(R.id.filterSpinner);
        filterSpinner.setAdapter(spinnerAdapter);

        filterSpinner.setOnItemSelectedListener(this);

        /* TODO the following code differs from the solution of the last assignment to resolve errors because the refresh button and the vegetarian checkbox are already gone
         * you may keep most of the business logic *
         * register click handler */
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        /* execute call to the OpenMensaAPI
         * note that the .enqueue(...) method is used instead of .execute() */
        openMensaAPI.getMeals(dateFormat.format(new Date())).enqueue(new Callback<List<Meal>>() {
            @Override
            public void onResponse(Call<List<Meal>> call, Response<List<Meal>> response) {
                /* check if response code was 2xx */
                if (response.isSuccessful()) {
                    /* remove elements from the adapter */
                    mealsArrayAdapter.clear();
                    /* unwrap the retrieved meals */
                    List<Meal> retrievedMeals = response.body();

                    /* Check if we should filter for vegetarian meals */
                    /* TODO please fix me - I'm totally useless because there's no checkbox anymore */
//
                    String s = (String) spinnerAdapter.getItem(position);
                    List<Meal> result = FilterFactory
                            .getFilter(s)
                            .filter(retrievedMeals);

                    mealsArrayAdapter.addAll(result);

                } else {
                    /* display an error message if response code was not 2xx */
                    Toast.makeText(
                            MainActivity.this,
                            R.string.api_failure_toast,
                            Toast.LENGTH_LONG
                    ).show();
                }
            }

            @Override
            public void onFailure(Call<List<Meal>> call, Throwable t) {
                /* display an error message if call failed */
                Toast.makeText(
                        MainActivity.this,
                        R.string.api_failure_toast,
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
