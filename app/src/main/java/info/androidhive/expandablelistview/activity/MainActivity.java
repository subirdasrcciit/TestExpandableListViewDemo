package info.androidhive.expandablelistview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import info.androidhive.expandablelistview.R;
import info.androidhive.expandablelistview.adapter.ExpandableListAdapter;
import info.androidhive.expandablelistview.adapter.OnChildItemSelectedListener;

public class MainActivity extends Activity implements OnChildItemSelectedListener, ExpandableListView.OnGroupExpandListener {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        expListView.setOnGroupExpandListener(this);
    }

    @Override
    public void onGroupExpand(int groupPosition) {
        int len = listAdapter.getGroupCount();

        for (int i = 0; i < len; i++) {
            if (i != groupPosition) {
                expListView.collapseGroup(i);
            }
        }
    }

    @Override
    public void onChildItemSelected(int position) {
        Toast.makeText(getApplicationContext(),
                "Child Clicked item position : " + position,
                Toast.LENGTH_SHORT).show();
    }


    /*
 * Preparing the list data
 */
    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding header data
        listDataHeader.add("Starter");
        listDataHeader.add("Main Course");
        listDataHeader.add("Dessert");

        // Adding child data
        List<String> starterList = new ArrayList<String>();
        starterList.add("Kakori Kebab");
        starterList.add("Tangri Kebabs");
        starterList.add("Chicken Satay");

        // Adding child data
        List<String> mainCourseList = new ArrayList<String>();
        mainCourseList.add("Biryani");
        mainCourseList.add("Butter chicken");
        mainCourseList.add("Chicken Tikka");

        // Adding child data
        List<String> dessertList = new ArrayList<String>();
        dessertList.add("Badam halwa");
        dessertList.add("Kheer");
        dessertList.add("Jalebi");


        listDataChild.put(listDataHeader.get(0), starterList); // Header, Child data
        listDataChild.put(listDataHeader.get(1), mainCourseList);
        listDataChild.put(listDataHeader.get(2), dessertList);
    }
}
