package notificator.company.business.unii.mrroll.service.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Categories {

    @SerializedName("categories")
    private final List<Category> categoryList;

    public Categories(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }
}
