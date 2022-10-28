# play-service-location-21-behavior-change

# Small Sample Code(debug build)
https://github.com/matsudamper/play-service-location-21-behavior-change/blob/d70d6c38551e4f6bcf249662fbd7f28cefbb7023/app/build.gradle#L58-L59

```
FATAL EXCEPTION: main
Process: com.example.myapplication, PID: 1613
java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
  at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:558)
  at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:936)
Caused by: java.lang.reflect.InvocationTargetException
  at java.lang.reflect.Method.invoke(Native Method)
  at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:548)
  at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:936) 
Caused by: com.google.android.gms.common.api.ApiException: 6: RESOLUTION_REQUIRED
  at com.google.android.gms.common.api.internal.TaskUtil.setResultOrApiException(com.google.android.gms:play-services-base@@18.0.1:4)
  at com.google.android.gms.internal.location.zzcq.zzb(com.google.android.gms:play-services-location@@21.0.0:2)
  at com.google.android.gms.internal.location.zzr.zza(com.google.android.gms:play-services-location@@21.0.0:3)
  at com.google.android.gms.internal.location.zzb.onTransact(com.google.android.gms:play-services-location@@21.0.0:3)
  at android.os.Binder.execTransactInternal(Binder.java:1285)
  at android.os.Binder.execTransact(Binder.java:1244)
```

# Production Code Error(debug build)
```
Unable to update local snapshot for com.google.android.libraries.consentverifier#me.retty, may result in stale flags.
java.util.concurrent.ExecutionException: java.lang.SecurityException: GoogleCertificatesRslt: not allowed: pkg=<pkg>, sha256=[<sha256>], atk=false, ver=<ver_code>.true (go/gsrlt)
  at aig.s(:com.google.android.gms.dynamite_mapsdynamite@224212044@22.42.12 (190400-0):3)
  at aig.get(:com.google.android.gms.dynamite_mapsdynamite@224212044@22.42.12 (190400-0):2)
  at ajq.g(:com.google.android.gms.dynamite_mapsdynamite@224212044@22.42.12 (190400-0):2)
  at yc.d(:com.google.android.gms.dynamite_mapsdynamite@224212044@22.42.12 (190400-0):1)
  at ye.run(:com.google.android.gms.dynamite_mapsdynamite@224212044@22.42.12 (190400-0):0)
  at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:463)
  at java.util.concurrent.FutureTask.run(FutureTask.java:264)
  at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:307)
  at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1137)
  at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:637)
  at java.lang.Thread.run(Thread.java:1012)
```
