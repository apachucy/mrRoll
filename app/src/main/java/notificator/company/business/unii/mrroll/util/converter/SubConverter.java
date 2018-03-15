package notificator.company.business.unii.mrroll.util.converter;


public interface SubConverter<T, C> {
    T convertSub(C object, int masterId);
}
