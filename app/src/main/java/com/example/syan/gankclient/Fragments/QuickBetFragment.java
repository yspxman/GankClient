package com.example.syan.gankclient.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.syan.gankclient.Common.MyAppConfig;
import com.example.syan.gankclient.CommonPager.CommonViewPager;
import com.example.syan.gankclient.Models.Banner;
import com.example.syan.gankclient.Models.Promotion;
import com.example.syan.gankclient.Models.QuickBet;
import com.example.syan.gankclient.Models.SisterModel;
import com.example.syan.gankclient.R;
import com.example.syan.gankclient.Services.BettingService;
import com.example.syan.gankclient.Services.SisterAPI;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link QuickBetFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuickBetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuickBetFragment extends Fragment implements View.OnClickListener{

    private Button showBtn;
    private Button refreshBtn;
    private int curPos = 0;
    private SisterAPI sisterAPI;
    private int page = 1;
    private ArrayList<SisterModel> data;
    private ProgressDialog dialog;
    private ProgressBar progressBar;
    private CommonViewPager commonViewPager;
    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private RecyclerView sportsRecyclerView;


    public QuickBetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuickBetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuickBetFragment newInstance(String param1, String param2) {
        QuickBetFragment fragment = new QuickBetFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sisterAPI = new SisterAPI();

    }

    private void initData() {

        progressBar.setVisibility(View.VISIBLE);
        data = new ArrayList<>();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyAppConfig.BaseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BettingService service = retrofit.create(BettingService.class);
        Call<QuickBet> quickBetCall = service.getQuickBets();

        // api call back
        quickBetCall.enqueue(new Callback<QuickBet>() {
            @Override
            public void onResponse(Call<QuickBet> call, Response<QuickBet> response) {

                progressBar.setVisibility(View.INVISIBLE);

                QuickBet bet = response.body();
                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT ).show();

                // set silder
                if (bet != null){

                    // set banner
                    Banner banner = bet.Banner;
                    if (banner != null){
                        ArrayList<Promotion> promos = banner.Promos;
                        ArrayList<String> images = new ArrayList<>();
                        for (int i=0; i< promos.size(); i++)     {
                            images.add(promos.get(i).imageUrl);
                        }
                        commonViewPager.setImages(images);
                    }

                    // set next races
                    recyclerView.setAdapter(new NextEventAdapter(bet.Races));
                    sportsRecyclerView.setAdapter(new NextEventAdapter(bet.NextToPlay));

                }
            }

            @Override
            public void onFailure(Call<QuickBet> call, Throwable t)
            {
                progressBar.setVisibility(View.INVISIBLE);

                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quick_bet, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.list_racing);
        sportsRecyclerView = (RecyclerView)view.findViewById(R.id.list_sports);
        showBtn = (Button)view.findViewById(R.id.next_btn);
        refreshBtn = (Button)view.findViewById(R.id.refresh_btn);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        commonViewPager = (CommonViewPager) view.findViewById(R.id.my_viewpager);
        showBtn.setOnClickListener(this);
        refreshBtn.setOnClickListener(this);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        layoutManager1.setOrientation(OrientationHelper.VERTICAL);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setOrientation(OrientationHelper.VERTICAL);

        sportsRecyclerView.setLayoutManager(layoutManager1);
        recyclerView.setLayoutManager(layoutManager2);

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next_btn:
                commonViewPager.next();
                break;
            case R.id.refresh_btn:
                initData();
                break;

        }
    }


    private class SisterTask extends AsyncTask<Void, Void, ArrayList<SisterModel>> {
        private int _page;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //progressBar.setVisibility(View.VISIBLE);
        }

        public SisterTask(int page){
            this._page = page;
        }

        @Override
        protected ArrayList<SisterModel> doInBackground(Void... voids) {
            return sisterAPI.fetchSister(10, _page);
        }

        @Override
        protected void onPostExecute(ArrayList<SisterModel> sisterModels) {
            super.onPostExecute(sisterModels);

            if (sisterModels == null){
                return;
            }

            data.clear();
            data.addAll(sisterModels);
            page++;
            curPos = 0;
            //loader.load(showImg, data.get(curPos).getUrl());
            //progressBar.setVisibility(View.INVISIBLE);

            // set silder
            ArrayList<String> images = new ArrayList<>();

            for (int i=0; i< sisterModels.size(); i++)     {
                images.add(sisterModels.get(i).getUrl());
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        private void dismissDlg()
        {
            if (dialog != null) {
                dialog.dismiss();
                dialog = null;
            }
        }
    }

}
