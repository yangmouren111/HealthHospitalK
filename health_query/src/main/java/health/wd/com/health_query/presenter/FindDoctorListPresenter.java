package health.wd.com.health_query.presenter;

import com.wd.common.core.DataCall;
import com.wd.common.core.WDPresenter;
import com.wd.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * Time:  2019-12-12
 * Author:  杨世博
 * Description:
 */
public class FindDoctorListPresenter extends WDPresenter<IAppRequest> {
    public FindDoctorListPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findDoctorList((int)args[0],(int)args[1],(int)args[2],(int)args[3],(int)args[4]);
    }
}
