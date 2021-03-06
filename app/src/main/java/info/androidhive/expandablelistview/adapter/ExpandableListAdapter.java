package info.androidhive.expandablelistview.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import info.androidhive.expandablelistview.R;
import info.androidhive.expandablelistview.customviews.CircleImageView;
import info.androidhive.expandablelistview.util.Constants;
import info.androidhive.expandablelistview.util.Utility;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    private OnChildItemSelectedListener onChildItemSelectedListener;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.onChildItemSelectedListener = (OnChildItemSelectedListener) _context;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView titleTxt = (TextView) convertView
                .findViewById(R.id.titleTxt);

        titleTxt.setText(childText);


        CircleImageView BannerImg = (CircleImageView) convertView
                .findViewById(R.id.BannerImg);
        Utility.setCircularImageBorderColor(BannerImg, 5, Constants.ORANGE_COLOR);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChildItemSelectedListener.onChildItemSelected(childPosition);
            }
        });


        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView grpHeaderTxt = (TextView) convertView
                .findViewById(R.id.grpHeaderTxt);
        grpHeaderTxt.setTypeface(null, Typeface.BOLD);
        grpHeaderTxt.setText(headerTitle);

        ImageView expandedImg = (ImageView) convertView
                .findViewById(R.id.expandedImg);

        if (isExpanded) {
            expandedImg.setImageResource(R.drawable.minus);
        } else {
            expandedImg.setImageResource(R.drawable.add);
        }


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
