package health.wd.com.health_query.presenter;

import com.wd.common.core.DataCall;
import com.wd.common.core.WDPresenter;
import com.wd.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * Time:  2019-12-16
 * Author:  杨世博
 * Description:
 */
public class ConsultDoctorPresenter extends WDPresenter<IAppRequest> {
    public ConsultDoctorPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.consultDoctor((String) args[0], (String) args[1], (int)args[2]);
    }
}
