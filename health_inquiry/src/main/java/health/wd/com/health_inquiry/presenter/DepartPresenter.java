package health.wd.com.health_inquiry.presenter;

import com.wd.common.core.DataCall;
import com.wd.common.core.WDPresenter;
import com.wd.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * Time:  2019-12-07
 * Author:  杨世博
 * Description:
 */
public class DepartPresenter extends WDPresenter<IAppRequest> {
    public DepartPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findDepartment();
    }
}