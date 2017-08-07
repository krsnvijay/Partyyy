package com.notadeveloper.app.pat;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PartyFragment extends Fragment {

  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";
  public static RecyclerView recyclerview;
  final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
  users u = new users();
  String cat;
  Query ref;
  LinearLayoutManager mLayoutManager;
  boolean called = false;
  Calendar c = Calendar.getInstance();
  private FirebaseRecyclerAdapter<party, PartyHolder> mAdapter;
  private List<party> pli = new ArrayList<>();
  // TODO: Rename parameter arguments, choose names that match
  private Menu menu;
  private DatabaseReference mDatabase;
  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;
  private OnFragmentInteractionListener mListener;

  public PartyFragment() {

  }

  // TODO: Rename and change types and number of parameters
  public static PartyFragment newInstance(String param1, String param2) {
    PartyFragment fragment = new PartyFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    return inflater.inflate(R.layout.fragment_party, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    recyclerview = view.findViewById(R.id.recyclerview);
    getUser();

    mLayoutManager = new LinearLayoutManager(getActivity());
    recyclerview.setLayoutManager(mLayoutManager);
    recyclerview.setHasFixedSize(true);
    mDatabase = FirebaseDatabase.getInstance().getReference();

    final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    mDatabase.keepSynced(true);

    ref = mDatabase.child("parties").orderByChild("partytime").startAt(c.getTimeInMillis() / 1000);
    Log.e("Fragment", "onViewCreated: " + (c.getTimeInMillis() / 1000));
    if (!called) {
      recyclerview.setLayoutManager(mLayoutManager);
      mAdapter = new FirebaseRecyclerAdapter<party, PartyHolder>(party.class, R.layout.partycard2,
          PartyHolder.class, ref) {
        @Override
        public void populateViewHolder(PartyHolder ViewHolder, final party product, int position) {
          ViewHolder.setData(product, getActivity());
        }
      };

      recyclerview.setAdapter(mAdapter);
      called = true;
    }
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

  void getUser() {
    FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
    final String uid = mUser.getUid();
    final DatabaseReference mDatabase =
        FirebaseDatabase.getInstance().getReference().child("users").child(uid);

    mDatabase.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        u = dataSnapshot.getValue(users.class);
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });
  }

  void setmAdapter(int x)

  {
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    Query q = mDatabase.child("parties");
    LinearLayoutManager LayoutManager = new LinearLayoutManager(getContext());

    if (x == 1) {
      q = mDatabase.child("parties").orderByChild("partytime").startAt(c.getTimeInMillis() / 1000);
      LayoutManager.setStackFromEnd(true);
      LayoutManager.setReverseLayout(true);
    } else if (x == 2) {
      q = mDatabase.child("parties").orderByChild("partytime").startAt(c.getTimeInMillis() / 1000);
    } else if (x == 3) {
      q = mDatabase.child("parties").orderByChild("pricestag").startAt(c.getTimeInMillis() / 1000);
      LayoutManager.setStackFromEnd(true);
      LayoutManager.setReverseLayout(true);
    } else if (x == 4) {
      q = mDatabase.child("parties").orderByChild("pricestag").startAt(c.getTimeInMillis() / 1000);
    }
    mAdapter = new FirebaseRecyclerAdapter<party, PartyHolder>(party.class, R.layout.partycard2,
        PartyHolder.class, q) {
      @Override
      public void populateViewHolder(PartyHolder ViewHolder, final party product, int position) {
        ViewHolder.setData(product, getContext());
      }
    };
    recyclerview.setLayoutManager(LayoutManager);
    recyclerview.setAdapter(mAdapter);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
      case R.id.datehigh:
        setmAdapter(1);
        return true;

      case R.id.datelow:
        setmAdapter(2);
        return true;
      case R.id.costhigh:
        setmAdapter(3);
        return true;
      case R.id.costlow:
        setmAdapter(4);
        return true;

      default:
        return super.onOptionsItemSelected(item);
    }
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
}
