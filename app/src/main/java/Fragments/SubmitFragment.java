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


    public class SubmitFragment extends Fragment {
        private TextView tvReviewPreview;
        private Button btnEditReview;
        private Button btnSubmitReview;


        public SubmitFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_submit_fragment, container, false);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            // Setup any handles to view objects here
            // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
            tvReviewPreview = view.findViewById(R.id.tvWelcome);
            btnEditReview = view.findViewById(R.id.btnEditReview);
            btnSubmitReview = view.findViewById(R.id.btnSubmit);

            btnSubmitReview.setOnClickListener(new View.OnClickListener() {
                FragmentManager fragmentManager= getActivity().getSupportFragmentManager();

                @Override
    //moving to the next fragment
                public void onClick(View view) {
                    fragmentManager.beginTransaction().replace(R.id.flContainer, new SubmitNavigationFragment()).commit();

                }
            });
        }
    }