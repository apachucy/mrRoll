package notificator.company.business.unii.mrroll.service.model;


import com.google.gson.annotations.SerializedName;

public class Topic {
    @SerializedName("id")
    private final int id;
    @SerializedName("name")
    private final String name;

    public Topic(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
