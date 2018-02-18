package notificator.company.business.unii.mrroll.service.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {
    @SerializedName("id")
    private final int id;

    @SerializedName("name")
    private final String name;

    @SerializedName("topics")
    private final List<Topic> topicList;

    public Category(int id, String name, List<Topic> topicList) {
        this.id = id;
        this.name = name;
        this.topicList = topicList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }
}
