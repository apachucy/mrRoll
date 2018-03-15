package notificator.company.business.unii.mrroll.util.converter;

import com.mikepenz.fastadapter.IItem;

import notificator.company.business.unii.mrroll.adapter.item.CheckBoxSubItem;
import notificator.company.business.unii.mrroll.service.model.Topic;

public class AdapterSubItemConverter implements SubConverter<IItem, Topic> {
    @Override
    public IItem convertSub(Topic object, int masterId) {
        IItem item = new CheckBoxSubItem(object.getName(), object.getId(), masterId);
        item.withIdentifier(object.getId());
        return item;
    }
}