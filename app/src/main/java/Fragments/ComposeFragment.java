package Fragments;

import android.os.Bundle;

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
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.parceler.Parcels;

import java.io.File;


public class ComposeFragment extends Fragment {
    public static final String TAG = "ComposeFragment";
    private TextView tvNewReview;
    private EditText etProductType;
    private EditText  etBrandName;
    private EditText  etItemName;
    private EditText etPurchaseStore;
    private EditText etPurchaseMode;
    private EditText etPrice;
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
        etPurchaseStore = view.findViewById(R.id. etPurchaseStore );
        etPurchaseMode = view.findViewById(R.id.etPurchaseMode);
        etPrice = view.findViewById(R.id.etPrice);
        btnext= view.findViewById(R.id.btnext);



//so when the user clicks on the next button let us collect all the information
//After collecting all the information we can now move to the next page

        btnext.setOnClickListener(new View.OnClickListener() {
         ;
          FragmentManager fragmentManager= getActivity().getSupportFragmentManager();

            @Override
            public void onClick(View view) {
                //collects the information entered by the user
                String storeName = etPurchaseStore.getText().toString();
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

    public void saveReview(String storeName, String brand, String productName, int price, String PurchaseMode, String productType, ParseUser currentUser) {
    //let's us create a new review with this
        ((HomeScreenActivity)getActivity()).review.setKeyProductType(productType);
        ((HomeScreenActivity)getActivity()).review.setKeyItemName(productName);
        ((HomeScreenActivity)getActivity()).review.setKeyBrand(brand);
        ((HomeScreenActivity)getActivity()).review.setKeyStoreName(storeName);
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
                etPurchaseStore.setText("");
                etPurchaseMode.setText("");
                etPrice.setText("");
                etBrandName.setText("");
                //if the image is saved successfully then we clear it



            }
        });
    }


}





//fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
// return true;




