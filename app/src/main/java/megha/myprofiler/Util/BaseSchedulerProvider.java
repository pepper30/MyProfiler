package megha.myprofiler.Util;

import io.reactivex.Scheduler;

/**
 * Created by Megha Chauhan on 16-Dec-17.
 */

public interface BaseSchedulerProvider {

    Scheduler computation();

    Scheduler io();

    Scheduler ui();
}
