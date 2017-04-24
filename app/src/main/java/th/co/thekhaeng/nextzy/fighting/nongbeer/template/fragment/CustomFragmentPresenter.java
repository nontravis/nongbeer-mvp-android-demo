package th.co.thekhaeng.nextzy.fighting.nongbeer.template.fragment;


import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.BaseMvpPresenter;

public class CustomFragmentPresenter extends BaseMvpPresenter<CustomFragmentInterface.View>
        implements CustomFragmentInterface.Presenter{

    public static CustomFragmentInterface.Presenter create(){
        return new CustomFragmentPresenter();
    }

}
