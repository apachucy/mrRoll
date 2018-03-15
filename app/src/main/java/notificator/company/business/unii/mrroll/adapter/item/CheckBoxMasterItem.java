package notificator.company.business.unii.mrroll.adapter.item;


import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IExpandable;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.ISubItem;
import com.mikepenz.fastadapter.expandable.items.AbstractExpandableItem;
import com.mikepenz.fastadapter.listeners.ClickEventHook;
import com.mikepenz.fastadapter.listeners.OnClickListener;
import com.mikepenz.materialize.holder.StringHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import notificator.company.business.unii.mrroll.R;

public class CheckBoxMasterItem<Parent extends IItem & IExpandable, SubItem extends IItem & ISubItem> extends AbstractExpandableItem<CheckBoxMasterItem<Parent, SubItem>, CheckBoxMasterItem.ViewHolder, SubItem> {

    private StringHolder categoryName;
    private StringHolder notificationNumber;
    private int categoryId;

    private OnClickListener<CheckBoxMasterItem> onClickListener;

    public CheckBoxMasterItem(String categoryName, String notificationNumber, int categoryId) {
        this.categoryId = categoryId;
        this.categoryName = new StringHolder(categoryName);
        this.notificationNumber = new StringHolder(notificationNumber);

    }

    @Override
    public void bindView(ViewHolder viewHolder, List<Object> payloads) {
        super.bindView(viewHolder, payloads);

        viewHolder.name.setChecked(isSelected());
        //set the text for the categoryName
        StringHolder.applyTo(categoryName, viewHolder.name);
        StringHolder.applyTo(notificationNumber, viewHolder.notificationNumber);

        if (getSubItems() == null || getSubItems().size() == 0) {
            viewHolder.icon.setVisibility(View.GONE);
        } else {
            viewHolder.icon.setVisibility(View.VISIBLE);
        }

        if (isExpanded()) {
            viewHolder.icon.setRotation(0);
            // ViewCompat.setRotation(viewHolder.icon, 0);
        } else {
            viewHolder.icon.setRotation(180);
            // ViewCompat.setRotation(viewHolder.icon, 180);
        }
    }

    @Override
    public int getType() {
        return R.id.fastadapter_expandable_item_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.expandable_category_item;
    }


    @Override
    public void unbindView(ViewHolder holder) {
        super.unbindView(holder);
        holder.name.setText(null);
        holder.notificationNumber.setText(null);
        //make sure all animations are stopped
        holder.icon.clearAnimation();
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }


    protected static class ViewHolder extends RecyclerView.ViewHolder {
        final View view;
        @BindView(R.id.checkbox_section_title)
        CheckBox name;
        @BindView(R.id.text_section_notification_number)
        TextView notificationNumber;
        @BindView(R.id.indication_arrow)
        ImageView icon;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }

    public void setOnClickListener(OnClickListener<CheckBoxMasterItem> onClickListener) {
        this.onClickListener = onClickListener;
    }

    public OnClickListener<CheckBoxMasterItem> getOnClickListener() {
        return onClickListener;
    }

    //we define a clickListener in here so we can directly animate
    final private OnClickListener<CheckBoxMasterItem<Parent, SubItem>> onClickItemListener = new OnClickListener<CheckBoxMasterItem<Parent, SubItem>>() {
        @Override
        public boolean onClick(View v, IAdapter adapter, @NonNull CheckBoxMasterItem item, int position) {
            if (item.getSubItems() != null) {
                if (!item.isExpanded()) {
                    ViewCompat.animate(v.findViewById(R.id.indication_arrow)).rotation(180).start();
                } else {
                    ViewCompat.animate(v.findViewById(R.id.indication_arrow)).rotation(0).start();
                }
                return onClickListener == null || onClickListener.onClick(v, adapter, item, position);
            }
            return onClickListener != null && onClickListener.onClick(v, adapter, item, position);
        }
    };

    /**
     * we overwrite the item specific click listener so we can automatically animate within the item
     *
     * @return
     */
    @Override
    public OnClickListener<CheckBoxMasterItem<Parent, SubItem>> getOnItemClickListener() {
        return onClickItemListener;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public static class CheckBoxMasterClickEvent extends ClickEventHook<CheckBoxMasterItem> {
        @Override
        public View onBind(@NonNull RecyclerView.ViewHolder viewHolder) {
            if (viewHolder instanceof CheckBoxMasterItem.ViewHolder) {
                return ((CheckBoxMasterItem.ViewHolder) viewHolder).name;
            }
            return null;
        }

        @Override
        public void onClick(View v, int position, FastAdapter<CheckBoxMasterItem> fastAdapter, CheckBoxMasterItem item) {
            fastAdapter.toggleSelection(position);
        }
    }

}
