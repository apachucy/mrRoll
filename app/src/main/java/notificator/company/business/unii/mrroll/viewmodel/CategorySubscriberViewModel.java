package notificator.company.business.unii.mrroll.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.mikepenz.fastadapter.expandable.ExpandableExtension;

import java.util.ArrayList;
import java.util.List;

import notificator.company.business.unii.mrroll.adapter.item.CheckBoxMasterItem;
import notificator.company.business.unii.mrroll.service.datasource.CategoryApi;
import notificator.company.business.unii.mrroll.service.model.Categories;
import notificator.company.business.unii.mrroll.service.model.Category;
import notificator.company.business.unii.mrroll.util.call.ApiResponse;
import notificator.company.business.unii.mrroll.util.converter.MasterConverter;

public class CategorySubscriberViewModel extends BaseViewModel {
    private CategoryApi categoryApi;
    private MasterConverter converter;

    private LiveData<ApiResponse<Categories>> allCategories;
    private MutableLiveData<List<IItem>> categoryItems;
    private Observer<ApiResponse<Categories>> categoryObserver;

    public CategorySubscriberViewModel(CategoryApi categoryApi, MasterConverter converter) {
        this.categoryApi = categoryApi;
        this.converter = converter;


        this.allCategories = this.categoryApi.getAllCategories();
        this.categoryItems = new MutableLiveData<>();
        this.categoryObserver = new Observer<ApiResponse<Categories>>() {
            @Override
            public void onChanged(@Nullable ApiResponse<Categories> apiResponse) {
                if (apiResponse.body != null && apiResponse.isSuccessful()) {
                    List<IItem> items = convertRequestToAdapter(apiResponse.body.getCategoryList());
                    //What next?
                    categoryItems.setValue(items);
                } else {
                    //TODO: Handle Error case
                }
            }
        };
        this.allCategories.observeForever(categoryObserver);
    }


    @Override
    protected void onCleared() {
        if (allCategories != null && allCategories.hasObservers()) {
            allCategories.removeObserver(categoryObserver);
        }
        super.onCleared();

    }


    private List<IItem> convertRequestToAdapter(List<Category> listResponse) {
        List<IItem> listAdapterData = new ArrayList<>();
        for (Category category : listResponse) {
            CheckBoxMasterItem convertedCategory = (CheckBoxMasterItem) converter.convert(category);
            listAdapterData.add(convertedCategory);
        }
        return listAdapterData;
    }

    @Override
    void openNextActivity(AppCompatActivity activity) {

    }

    public LiveData<List<IItem>> getCategoryItems() {
        return categoryItems;
    }


}
