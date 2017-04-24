package th.co.thekhaeng.nextzy.fighting.nongbeer.template.activity;


import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.BaseMvpPresenter;

public class CustomActivityPresenter extends BaseMvpPresenter<CustomActivityInterface.View>
        implements CustomActivityInterface.Presenter{

    public static CustomActivityInterface.Presenter create(){
        return new CustomActivityPresenter();
    }

}
