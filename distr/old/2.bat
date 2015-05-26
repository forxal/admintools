FOR /F %%I IN ("%0") DO SET BATDIR=%%~dpI
CD /D %BATDIR%
runas /user:crimea\adm_mas cmd
