package notificator.company.business.unii.mrroll.util.converter;


import com.mikepenz.fastadapter.IItem;

import java.util.LinkedList;
import java.util.List;

import notificator.company.business.unii.mrroll.adapter.item.CheckBoxMasterItem;
import notificator.company.business.unii.mrroll.service.model.Category;
import notificator.company.business.unii.mrroll.service.model.Topic;

public class AdapterMasterItemConverter implements MasterConverter<CheckBoxMasterItem, Category> {
    private SubConverter subConverter;

    public AdapterMasterItemConverter(SubConverter subConverter) {
        this.subConverter = subConverter;
    }

    @Override
    public CheckBoxMasterItem convert(Category object) {
        String notificationNumber = "(" + object.getTopicList().size() + ")";
        CheckBoxMasterItem item = new CheckBoxMasterItem<>(object.getName(), notificationNumber, object.getId());
        item.withIdentifier(object.getId());
        List<IItem> subItemList = new LinkedList<>();
        for (Topic topic : object.getTopicList()) {
            IItem subItem = (IItem) subConverter.convertSub(topic, object.getId());
            subItemList.add(subItem);
        }
        item.withSubItems(subItemList);
        return item;
    }
}
