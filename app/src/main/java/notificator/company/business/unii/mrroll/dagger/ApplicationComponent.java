package notificator.company.business.unii.mrroll.dagger;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ApplicationModule.class})
@Singleton
public interface ApplicationComponent {

    /**
     * The application context
     *
     * @return the context.
     */
    Context provideContext();

}

