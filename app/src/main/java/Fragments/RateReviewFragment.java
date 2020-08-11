package Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.alstarapp.HomeScreenActivity;
import com.example.alstarapp.R;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.SaveCallback;

import java.io.File;

import static android.app.Activity.RESULT_OK;


public class RateReviewFragment extends Fragment {
    public static final String TAG = "RateReviewFragment";
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public String photoFileName = "photo.jpg";
    private TextView tvNewReview;
    private TextView tvCaptureImage;
    private ImageView ivItemPicture;
    private TextView tvRate;
    private RatingBar rbItemReview;
    private Button btnNext;
    private File photoFile;
    private String fileName;


    public RateReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_rate_review, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        tvCaptureImage = view.findViewById(R.id.tvCaptureImage);
        ivItemPicture = view.findViewById(R.id.ivItemPicture);
        tvRate = view.findViewById(R.id.tvRate);
        rbItemReview = view.findViewById(R.id.rbItemReview);
        btnNext = view.findViewById(R.id.btnNext);


        tvCaptureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCamera();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
                                       FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                                       public void onClick(View view) {
                                           //let us save the photo
                                           Float prodcutReview = rbItemReview.getRating();
                                           savePhoto();
                                           saveRating(prodcutReview);
                                           fragmentManager.beginTransaction().replace(R.id.flContainer, new CustomerExperienceFragment()).commit();

                                       }
                                       // ParseFile image =  ivItemPicture.getPars;
                                   }
        );
    }

    private void saveRating(Float productReview) {
        ((HomeScreenActivity) getActivity()).review.setKeyRating(productReview);
    }

    private void savePhoto() {
        ((HomeScreenActivity) getActivity()).review.setImage(new ParseFile(photoFile));
        ((HomeScreenActivity) getActivity()).review.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

            }
        });

    }

    private void launchCamera() {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Create a File reference for future access
        // photoFile = getPhotoFileUri(photoFileName+System.currentTimeMillis() + ".png");

        photoFile = new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        Uri fileProvider = FileProvider.getUriForFile(getContext(), "com.codepath.fileprovider", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            // Start the image capture intent to take photo
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }

    }

    //We are going to have our image taken by the user
    //this method will be invoke when the child Application returns to the parent Application, the child Application being the camera and the parent application being our app
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            System.out.println("data is: ");
            System.out.println(data.toString());
            if (resultCode == RESULT_OK) {
                // by this point we have the camera photo on disk
                Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                // RESIZE BITMAP, see section below
                // Load the taken image into a preview

                ivItemPicture.setImageBitmap(takenImage);
            } else { // Result was a failure
                System.out.println("got the error code: " + resultCode);
                Toast.makeText(getContext(), "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private File getPhotoFileUri(String photoFileName) {

        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);

        // Create the storage directory if it does not exist

        // Return the file target for the photo based on filename
        File file = new File(mediaStorageDir.getPath() + File.separator + fileName);

        return file;
    }
}


//give me the most recent review. from the backend
//change the variable. update the specific value