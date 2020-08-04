    package Fragments;

    import android.content.Intent;
    import android.os.Bundle;

    import androidx.fragment.app.Fragment;
    import androidx.fragment.app.FragmentManager;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.ImageView;
    import android.widget.TextView;

    import com.example.alstarapp.LoginActivity;
    import com.example.alstarapp.R;
    import com.parse.ParseUser;


    public class ProfileFragment extends Fragment {

        private ImageView ivProfile;
            private TextView tvProfile;
            private TextView tvMessages;
            private TextView tvMyReviews;
            private Button btnLogout;



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_profile, container, false);



        }
        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            // Setup any handles to view objects here
            // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
            ivProfile= view.findViewById(R.id.ivProfile);
            tvProfile = view.findViewById(R.id.tvProfile);
            tvMessages = view.findViewById(R.id.tvMessages);
            tvMyReviews = view.findViewById(R.id.tvMyReviews);
            btnLogout = view.findViewById(R.id.btnLogout);


         //clicking on my reviews section takes you to the reviews section
            tvMyReviews.setOnClickListener(new View.OnClickListener() {
                 FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                @Override
                public void onClick(View view) {

    //move to the next fragment
                    fragmentManager.beginTransaction().replace(R.id.flContainer, new MyReviewFragment()).commit();
                }
            });







            //clicking on the messages takes you to the messages section
         tvMessages.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View view) {

             }

         });
            //fragmentManager.beginTransaction().replace(R.id.flContainer, new MyMessagesFragment()).commit();



            //invoke an action when the user clicks on the logout button
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     onLogoutAction();
                }
            });



        }

        private void onLogoutAction() {
            ParseUser.logOutInBackground();
            Intent i = new Intent(getContext(), LoginActivity.class);
            startActivity(i);
        }


    }
    //making parse request




