package th.co.thekhaeng.nextzy.fighting.nongbeer.ui;


import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.BaseMvpPresenter;

public class SplashScreenActivityPresenter extends BaseMvpPresenter<SplashScreenActivityInterface.View>
        implements SplashScreenActivityInterface.Presenter{

    public static SplashScreenActivityInterface.Presenter create(){
        return new SplashScreenActivityPresenter();
    }
}
