package rapticon.tk.leavemanagement.adapter;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import rapticon.tk.leavemanagement.model.BaseElement;
import rapticon.tk.leavemanagement.util.RecycleAdapterElement;

/**
 * *   *  Created by Tharindu on 1/25/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    private ArrayList<BaseElement> mItems;
    private Activity mActivity;
    private int mType;
    private OnDeleteItem onDeleteItem;
    private OnLoadMore loadMore;
    private final static int FIRST = 0;
    private final static int OTHER = 1;
    private boolean isCompact;

    public interface OnDeleteItem {

        void delete(String id);
    }

    public interface OnLoadMore {
        void loadMore(int position);
    }

    public RecyclerAdapter(ArrayList<BaseElement> mItems, Activity mActivity, int mType) {
        this.mItems = mItems;
        this.mActivity = mActivity;
        this.mType = mType;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewHolder viewHolder = null;

        if (mType == RecycleAdapterElement.CAT.getType()) {

            /*viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.select_category_layout, parent, false));
*/
        }


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (mType == RecycleAdapterElement.CAT.getType()) {

           /* Category category = (Category) mItems.get(position);

            TextHelper textHelper = new TextHelper(mActivity);
            textHelper.setText(category.getTitle(), holder.title);*/

        }

    }

    @Override
    public int getItemViewType(int position) {

        if (mItems != null && (mItems.size() - 1) == position && loadMore != null)
            loadMore.loadMore(position);

        if (position == 0) {
            return FIRST;
        } else {
            return OTHER;
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView subTitle;
        public TextView timeView;
        public ImageView imageView;
        public ImageView subImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            if (mType == RecycleAdapterElement.CAT.getType()) {

               /* title = (TextView) itemView.findViewById(R.id.title);
                cardView = (CardView) itemView.findViewById(R.id.card_view);

                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Category category = (Category) mItems.get(getAdapterPosition());
                        onDeleteItem.delete(category.getId());
                        removeAt(getAdapterPosition());
                    }
                });*/
            }


        }

        public void removeAt(int position) {
            mItems.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mItems.size());
        }
    }

    /**
     * @param onDeleteItem value of onDeleteItem
     */

    public void setOnDeleteItem(OnDeleteItem onDeleteItem) {
        this.onDeleteItem = onDeleteItem;
    }

    /**
     * @param loadMore value of loadMore
     */

    public void setLoadMore(OnLoadMore loadMore) {
        this.loadMore = loadMore;
    }
}
