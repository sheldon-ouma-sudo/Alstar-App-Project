    package Fragments;

    import android.os.Bundle;

    import androidx.fragment.app.Fragment;
    import androidx.fragment.app.FragmentManager;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.TextView;

    import com.example.alstarapp.R;

    public class SubmitNavigationFragment extends Fragment {
       private TextView tvThankYou;
       private Button btnNavigate;
       private TextView tvConfirmation;
        public SubmitNavigationFragment() {
            // Required empty public constructor
        }



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_submit_navigation, container, false);
        }


        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            // Setup any handles to view objects here
            // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
            tvThankYou = view.findViewById(R.id.tvThankYou);
            btnNavigate = view.findViewById(R.id.btnNavigate);
            tvConfirmation = view.findViewById(R.id.tvConfirmation);


            btnNavigate.setOnClickListener(new View.OnClickListener() {
                FragmentManager fragmentManager= getActivity().getSupportFragmentManager();

                @Override
               //moving to the next fragment
                public void onClick(View view) {
                    fragmentManager.beginTransaction().replace(R.id.flContainer, new ProfileFragment()).commit();

                }
            });



        }



    }