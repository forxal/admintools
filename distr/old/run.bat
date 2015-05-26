@ECHO OFF
FOR /F %%I IN ("%0") DO SET BATDIR=%%~dpI
CD /D %BATDIR%
java -jar AdminTools.jar
@ECHO ON