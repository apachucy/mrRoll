package notificator.company.business.unii.mrroll;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.mikepenz.fastadapter.expandable.ExpandableExtension;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import notificator.company.business.unii.mrroll.adapter.item.CheckBoxMasterItem;
import notificator.company.business.unii.mrroll.viewmodel.CategorySubscriberViewModel;
import notificator.company.business.unii.mrroll.viewmodel.factory.CategorySubscriberViewModelFactory;

public class CategorySubscriberActivity extends AppCompatActivity {

    @Inject
    CategorySubscriberViewModelFactory viewModelFactory;

    @BindView(R.id.notification_list)
    RecyclerView notificationList;

    private CategorySubscriberViewModel viewModel;
    private ExpandableExtension<IItem> expandableExtension;
    private FastItemAdapter<IItem> fastItemAdapter;
    private Observer<List<IItem>> itemCategoryObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_center);
        ButterKnife.bind(this);

        this.viewModel = ViewModelProviders.of(this, viewModelFactory).get(CategorySubscriberViewModel.class);

        this.expandableExtension = new ExpandableExtension<>();
        this.fastItemAdapter = new FastItemAdapter<>();

        this.fastItemAdapter.withSelectable(true);
        this.fastItemAdapter.withMultiSelect(true);
        this.fastItemAdapter.withUseIdDistributor(false);
        this.fastItemAdapter.addExtension(expandableExtension);
        this.fastItemAdapter.withEventHook(new CheckBoxMasterItem.CheckBoxMasterClickEvent());

        this.notificationList.setLayoutManager(new LinearLayoutManager(this));
        this.notificationList.setAdapter(fastItemAdapter);

        this.itemCategoryObserver = new Observer<List<IItem>>() {
            @Override
            public void onChanged(@Nullable List<IItem> iItems) {
                fastItemAdapter.add(iItems);
                fastItemAdapter.notifyAdapterDataSetChanged();
            }
        };

        this.viewModel.getCategoryItems().observe(this, itemCategoryObserver);
    }
}
