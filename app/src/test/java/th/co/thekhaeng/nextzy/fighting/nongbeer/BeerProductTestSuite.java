package th.co.thekhaeng.nextzy.fighting.nongbeer;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by thekhaeng on 4/12/2017 AD.
 */

@RunWith( Suite.class )
@Suite.SuiteClasses( {
                             BeerProductFragmentPresenterUnitTest.class,
                             BeerProductFragmentPresenterIntegrationTest.class,
                             BeerProductListAdapterPresenterUnitTest.class
                     } )
public class BeerProductTestSuite{
}
