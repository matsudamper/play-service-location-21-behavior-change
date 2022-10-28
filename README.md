# play-service-location-21-behavior-change

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
