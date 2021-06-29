rd release /S /Q
del Info2D_platformer_win_x64.zip
mkdir release
xcopy .\res release\res /E /I /Y
xcopy "C:\Program Files (x86)\Java\jdk-11.0.2" release\jdk-11.0.2 /E /I /Y
copy launch.bat release\launch.bat
copy build\libs\Info_platformer2D_jumpNrun-1.0-SNAPSHOT.jar release\Info_2DPlatformer.jar
PAUSE
cd release
jdk-11.0.2\bin\java.exe -ea -jar Info_2DPlatformer.jar
cd ..
PAUSE
del release\res\data\login.json
7z a -tzip Info2D_platformer_win_x64.zip release
PAUSE
exit