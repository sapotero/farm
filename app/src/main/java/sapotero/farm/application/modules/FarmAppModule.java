package sapotero.farm.application.modules;

import android.app.Application;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class FarmAppModule {

  Application mApplication;

  public FarmAppModule(Application application) {
    mApplication = application;
  }

  @Provides
  @Singleton
  Application providesApplication() {
    return mApplication;
  }

  @Provides
  @Singleton
  EventBus providesEventBus() {
    return EventBus.getDefault();
  }

  @Provides
  @Singleton
  OkHttpClient provideOkHttpClient() {
    OkHttpClient client = new OkHttpClient();
    return client;
  }

}
