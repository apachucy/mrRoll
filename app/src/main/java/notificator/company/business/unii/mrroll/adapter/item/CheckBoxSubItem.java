package notificator.company.business.unii.mrroll.adapter.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IClickable;
import com.mikepenz.fastadapter.IExpandable;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.ISubItem;
import com.mikepenz.fastadapter.expandable.items.AbstractExpandableItem;
import com.mikepenz.fastadapter.listeners.ClickEventHook;
import com.mikepenz.materialize.holder.StringHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import notificator.company.business.unii.mrroll.R;

public class CheckBoxSubItem<Parent extends IItem & IExpandable & ISubItem & IClickable> extends AbstractExpandableItem<Parent, CheckBoxSubItem.ViewHolder, CheckBoxSubItem<Parent>> {
    private StringHolder topicName;
    private int topicId;
    private int categoryId;

    public CheckBoxSubItem(String text, int topicId, int categoryId) {
        this.topicName = new StringHolder(text);
        this.categoryId = categoryId;
        this.topicId = topicId;
    }

    @NonNull
    @Override
    public CheckBoxSubItem.ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public void bindView(ViewHolder viewHolder, List<Object> payloads) {
        super.bindView(viewHolder, payloads);

        viewHolder.topic.setChecked(isSelected());
        //set the text for the topicName
        StringHolder.applyTo(topicName, viewHolder.topic);
    }

    @Override
    public void unbindView(ViewHolder holder) {
        super.unbindView(holder);
        holder.topic.setText(null);
    }


    @Override
    public int getType() {
        return R.id.fastadapter_sub_item_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.topic_item;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getTopicId() {
        return topicId;
    }


    /**
     * our ViewHolder
     */
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected View view;
        @BindView(R.id.checkbox_topic_title)
        CheckBox topic;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }

    public static class CheckBoxClickEvent extends ClickEventHook<CheckBoxSubItem> {
        @Override
        public View onBind(@NonNull RecyclerView.ViewHolder viewHolder) {
            if (viewHolder instanceof CheckBoxSubItem.ViewHolder) {
                return ((CheckBoxSubItem.ViewHolder) viewHolder).topic;
            }
            return null;
        }

        @Override
        public void onClick(View v, int position, FastAdapter<CheckBoxSubItem> fastAdapter, CheckBoxSubItem item) {
            fastAdapter.toggleSelection(position);
        }
    }
}
