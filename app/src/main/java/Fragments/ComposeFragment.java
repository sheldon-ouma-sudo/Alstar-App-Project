package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alstarapp.HomeScreenActivity;
import com.example.alstarapp.R;
import com.example.alstarapp.Review;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.parceler.Parcels;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class ComposeFragment extends Fragment {
    public static final String TAG = "ComposeFragment";
    private TextView tvNewReview;
    private EditText etProductType;
    private EditText  etBrandName;
    private EditText  etItemName;
    private Button btnPurchaseStore;
    private EditText etPurchaseMode;
    private EditText etPrice;
    private static int AUTOCOMPLETE_REQUEST_CODE = 1;
    private String location;
    private TextView tvChosenStore;
    private ImageView photophile;
    private Button btnext;




    public ComposeFragment(EditText etProductType, EditText etPurchaseMode) {
       // this.etProductType = etProductType;
        // Required empty public constructor
        //this.etPurchaseMode = etPurchaseMode;
    }

    public ComposeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_compose, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        tvNewReview = view.findViewById(R.id.tvNewReview);
        etProductType = view.findViewById(R.id.etProductType);
        etBrandName= view.findViewById(R.id.etBrandName);
        etItemName = view.findViewById(R.id. etItemName);
        btnPurchaseStore = view.findViewById(R.id.btnPurchaseStore );
        etPurchaseMode = view.findViewById(R.id.etPurchaseMode);
        tvChosenStore= view.findViewById(R.id.tvChosenStore);
        etPrice = view.findViewById(R.id.etPrice);
        btnext= view.findViewById(R.id.btnext);

        //enable the user choose
        btnPurchaseStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the fields to specify which types of place data to
                // return after the user has made a selection.
                if (!Places.isInitialized()) {
                    Places.initialize(getContext(), "AIzaSyCmL3kKWVwuG7ZRrWEXF8cN8GLxdfVyG5Q", Locale.US);
                }
                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME);

                // Start the autocomplete intent.
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(getContext());
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
            }
        });

//so when the user clicks on the next button let us collect all the information
//After collecting all the information we can now move to the next page

        btnext.setOnClickListener(new View.OnClickListener() {
         ;
          FragmentManager fragmentManager= getActivity().getSupportFragmentManager();

            @Override
            public void onClick(View view) {
                //collects the information entered by the user
                String storeName = btnPurchaseStore.getText().toString();
                if(storeName.isEmpty()){
                    //do something here
                   //Toast.makeText(ComposeFragment.getContext(),"THE STORE NAME CAN NOT BE EMPTY",Toast.LENGTH_SHORT).show(); There is a bug here
                  return;
                }
                String brand = etBrandName.getText().toString();
                String productName = etItemName.getText().toString();
                int price = Integer.parseInt(etPrice.getText().toString());
                String PurchaseMode = etPurchaseMode.getText().toString();
                String productType = etProductType.getText().toString();
                //we want to know whoever is keying in the information
                ParseUser currentUser = ParseUser.getCurrentUser();
                Log.i(TAG,"THe current user is:" + currentUser.toString());
                //now let's save everything we have gotten from the user so far
                saveReview(storeName,brand, productName,price, PurchaseMode, productType,currentUser);
             //now that we have saved the detaiils to the back end we now need to send the review data to the next fragment

                fragmentManager.beginTransaction().replace(R.id.flContainer, new RateReviewFragment()).commit();


            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                location = place.getName();
                tvChosenStore.setText(location);
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(TAG, status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void saveReview(String storeName, String brand, String productName, int price, String PurchaseMode, String productType, ParseUser currentUser) {
    //let's us create a new review with this
        ((HomeScreenActivity)getActivity()).review.setKeyProductType(productType);
        ((HomeScreenActivity)getActivity()).review.setKeyItemName(productName);
        ((HomeScreenActivity)getActivity()).review.setKeyBrand(brand);
        ((HomeScreenActivity)getActivity()).review.setKeyStoreName(location);
        ((HomeScreenActivity)getActivity()).review.setKeyPurchaseMode(PurchaseMode);
        ((HomeScreenActivity)getActivity()).review.setKeyPrice(price);
        ((HomeScreenActivity)getActivity()).review.setKeyReviewer(currentUser);
        //whaat does saveInBackground do--prolly saving the review
        ((HomeScreenActivity)getActivity()).review.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                  //there must be an error while saving the data in the backend
                  Log.e(TAG,"Error while saving in the backend",e);
                }
                  //Otherwise the review was saved successfully
                Log.i(TAG,"Review data was saved succesfully");
                //if so then let us clear off the whatever text the user had  saved
                etProductType.setText("");
                etItemName.setText("");
                //etPurchaseStore.setText("");
                etPurchaseMode.setText("");
                etPrice.setText("");
                etBrandName.setText("");
                //if the image is saved successfully then we clear it



            }
        });
    }


}









